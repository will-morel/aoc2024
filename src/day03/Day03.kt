package day03

import println
import readInput
import test

typealias Operation = Pair<Int, Int>

fun main() {
    fun String.getOperations(): List<Operation> =
        """mul\((\d{1,3}),(\d{1,3})\)"""
            .toRegex()
            .findAll(this)
            .map {
                Pair(
                    it.groups[1]!!.value.toInt(),
                    it.groups[2]!!.value.toInt(),
                )
            }.toList()

    fun String.getEnabledOperations(): List<Operation> {
        val matches =
            """(don't)|mul\(\d{1,3},\d{1,3}\)|(do)"""
                .toRegex()
                .findAll(this)

        val operations = mutableListOf<Operation>()
        var enable = true
        for (operation in matches) {
            if (operation.value == "do") {
                enable = true
                continue
            }
            if (operation.value == "don't") {
                enable = false
                continue
            }
            if (enable) {
                operations.add(operation.value.getOperations().first())
            }
        }
        return operations
    }

    fun Operation.compute(): Int = this.first * this.second

    fun part1(input: List<String>): Int =
        input
            .joinToString()
            .getOperations()
            .sumOf {
                it.compute()
            }

    fun part2(input: List<String>): Int =
        input
            .joinToString()
            .getEnabledOperations()
            .sumOf {
                it.compute()
            }

    Operation(2, 4)
        .compute()
        .test(8)

    "mul(2,4)"
        .getOperations()
        .test(
            listOf(Operation(2, 4)),
        )

    "mul(2,4)------mul(4,8)"
        .getOperations()
        .test(
            listOf(
                Operation(2, 4),
                Operation(4, 8),
            ),
        )

    readInput("day03/test")
        .first()
        .getOperations()
        .test(
            listOf(
                Operation(2, 4),
                Operation(5, 5),
                Operation(11, 8),
                Operation(8, 5),
            ),
        )

    part1(
        readInput("day03/test"),
    ).test(161)

    readInput("day03/test2")
        .first()
        .getEnabledOperations()
        .test(
            listOf(
                Operation(2, 4),
                Operation(8, 5),
            ),
        )

    part2(
        readInput("day03/test2"),
    ).test(48)

    val input = readInput("day03/input")
    part1(input).println()
    part2(input).println()
}
