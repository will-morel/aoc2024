import kotlin.math.abs

fun main() {
    fun String.toIntList(): List<Int> = this.split(" ").map { it.toInt() }

    fun List<Int>.isAllIncreasing(): Boolean = this.sorted() == this

    fun List<Int>.isAllDecreasing(): Boolean = this.sorted().reversed() == this

    fun List<Int>.hasValidStep(): Boolean =
        this
            .zipWithNext()
            .mapNotNull { it.takeIf { abs(it.second - it.first) !in 1..3 } }
            .isEmpty()

    fun List<Int>.isSafe(): Boolean = (this.isAllIncreasing() || this.isAllDecreasing()) && this.hasValidStep()

    fun List<Int>.hasOneBadLevelOnly(): Boolean =
        List(this.size) { index ->
            val list = this.toMutableList()
            list.removeAt(index)
            list.isSafe()
        }.any { it }

    fun List<Int>.isSafeOrWithOneBadLevelOnly(): Boolean = this.isSafe() || this.hasOneBadLevelOnly()

    fun part1(input: List<String>) = input.map { it.toIntList() }.count { it.isSafe() }

    fun part2(input: List<String>) = input.map { it.toIntList() }.count { it.isSafeOrWithOneBadLevelOnly() }

    "1 2 4 5".toIntList().test(listOf(1, 2, 4, 5))

    listOf(1, 2, 3).isAllIncreasing().test(true)
    listOf(2, 3, 1).isAllIncreasing().test(false)

    listOf(1, 2, 3).isAllDecreasing().test(false)
    listOf(2, 3, 1).isAllDecreasing().test(true)

    listOf(1, 2, 3).hasValidStep().test(true)
    listOf(1, 2, 6).hasValidStep().test(false)

    listOf(3, 2, 1).hasValidStep().test(true)
    listOf(6, 2, 1).hasValidStep().test(false)

    listOf(1, 3, 2, 4, 5).hasOneBadLevelOnly().test(true)
    listOf(9, 7, 6, 2, 1).hasOneBadLevelOnly().test(false)

    part1(readInput("Day02_test")).test(2)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
