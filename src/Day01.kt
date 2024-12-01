fun main() {
    fun List<String>.splitAndSort(size: Int): Pair<List<Int>, List<Int>> =
        Pair(
            this.map { it.substring(0, size).toInt() }.sorted(),
            this.map { it.substring(it.length - size, it.length).toInt() }.sorted(),
        )

    fun part1(
        input: List<String>,
        size: Int,
    ): Int =
        input.splitAndSort(size).let { pairs ->
            pairs.first
                .mapIndexed { index, value ->
                    kotlin.math.abs(pairs.second[index] - value)
                }.sum()
        }

    fun part2(
        input: List<String>,
        size: Int,
    ): Int {
        val pairs = input.splitAndSort(size)
        return pairs.first.fold(0) { acc, value ->
            acc + (value * pairs.second.count { it == value })
        }
    }

    readInput("Day01_test")
        .splitAndSort(1)
        .test(
            Pair(
                listOf(1, 2, 3, 3, 3, 4),
                listOf(3, 3, 3, 4, 5, 9),
            ),
        )

    part1(readInput("Day01_test"), 1).test(11)

    val input = readInput("Day01")
    part1(input, 5).println()
    part2(input, 5).println()
}
