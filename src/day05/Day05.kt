package day05

import println
import readInput
import test
import kotlin.math.ceil

typealias Rule = Pair<Int, Int>
typealias Update = List<Int>

fun main() {
    fun List<String>.toRules(): List<Rule> =
        this.map {
            Pair(
                it.substringBefore('|').toInt(),
                it.substringAfter('|').toInt(),
            )
        }

    fun Update.getMiddlePage(): Int = this[ceil((this.size / 2).toDouble()).toInt()]

    fun Update.isValidFor(rules: List<Rule>) =
        this.zipWithNext().filter { rules.contains(it) }.let {
            // comparing list of pairs size with list of items size so need -1
            it.size == this.size - 1
        }

    fun updateOf(pages: String): Update = pages.split(',').map { it.toInt() }

    fun List<String>.getValidUpdates(rules: List<Rule>): List<Update> =
        this
            .map {
                updateOf(it)
            }.filter {
                it.isValidFor(rules)
            }

    fun List<String>.getInvalidUpdates(rules: List<Rule>): List<Update> =
        this
            .map {
                updateOf(it)
            }.filterNot {
                it.isValidFor(rules)
            }

    fun part1(
        rules: List<Rule>,
        updates: List<String>,
    ): Int =
        updates.getValidUpdates(rules).sumOf {
            it.getMiddlePage()
        }

    fun part2(
        rules: List<Rule>,
        updates: List<String>,
    ): Int =
        updates
            .getInvalidUpdates(rules)
            .map {
                it.sortedWith { a, b ->
                    when {
                        Pair(a, b) in rules -> -1
                        Pair(b, a) in rules -> 1
                        else -> 0
                    }
                }
            }.sumOf {
                it.getMiddlePage()
            }

    fun test() {
        listOf(
            "47|53",
            "97|13",
            "97|61",
        ).toRules().test(
            listOf(Rule(47, 53), Rule(97, 13), Rule(97, 61)),
        )

        updateOf("97,61,53,29,13").test(
            listOf(
                97,
                61,
                53,
                29,
                13,
            ),
        )
        updateOf("97,61,53,29,13").getMiddlePage().test(53)
        updateOf("75,29,13").getMiddlePage().test(29)

        val rules = readInput("day05/test-rules").toRules()
        updateOf("75,47,61,53,29").isValidFor(rules).test(true)

        updateOf("75,97,47,61,53").isValidFor(rules).test(false)

        val updates = readInput("day05/test-updates")
        part1(rules, updates).test(143)
    }

    test()

    val rules = readInput("day05/rules").toRules()
    val updates = readInput("day05/updates")
    part1(rules, updates).println()

    part2(rules, updates).println()
}
