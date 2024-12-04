package day04

import println
import readInput
import test

fun main() {
    fun right(
        input: List<String>,
        line: Int,
        column: Int,
    ): Boolean =
        try {
            input[line][column + 1] == 'M' &&
                input[line][column + 2] == 'A' &&
                input[line][column + 3] == 'S'
        } catch (e: StringIndexOutOfBoundsException) {
            false
        }

    fun left(
        input: List<String>,
        line: Int,
        column: Int,
    ): Boolean =
        try {
            input[line][column - 1] == 'M' &&
                input[line][column - 2] == 'A' &&
                input[line][column - 3] == 'S'
        } catch (e: StringIndexOutOfBoundsException) {
            false
        }

    fun up(
        input: List<String>,
        line: Int,
        column: Int,
    ): Boolean =
        try {
            input[line - 1][column] == 'M' &&
                input[line - 2][column] == 'A' &&
                input[line - 3][column] == 'S'
        } catch (e: StringIndexOutOfBoundsException) {
            false
        } catch (e: IndexOutOfBoundsException) {
            false
        }

    fun down(
        input: List<String>,
        line: Int,
        column: Int,
    ): Boolean =
        try {
            input[line + 1][column] == 'M' &&
                input[line + 2][column] == 'A' &&
                input[line + 3][column] == 'S'
        } catch (e: StringIndexOutOfBoundsException) {
            false
        } catch (e: IndexOutOfBoundsException) {
            false
        }

    fun upRight(
        input: List<String>,
        line: Int,
        column: Int,
    ): Boolean =
        try {
            input[line - 1][column + 1] == 'M' &&
                input[line - 2][column + 2] == 'A' &&
                input[line - 3][column + 3] == 'S'
        } catch (e: StringIndexOutOfBoundsException) {
            false
        } catch (e: IndexOutOfBoundsException) {
            false
        }

    fun upLeft(
        input: List<String>,
        line: Int,
        column: Int,
    ): Boolean =
        try {
            input[line - 1][column - 1] == 'M' &&
                input[line - 2][column - 2] == 'A' &&
                input[line - 3][column - 3] == 'S'
        } catch (e: StringIndexOutOfBoundsException) {
            false
        } catch (e: IndexOutOfBoundsException) {
            false
        }

    fun downRight(
        input: List<String>,
        line: Int,
        column: Int,
    ): Boolean =
        try {
            input[line + 1][column + 1] == 'M' &&
                input[line + 2][column + 2] == 'A' &&
                input[line + 3][column + 3] == 'S'
        } catch (e: StringIndexOutOfBoundsException) {
            false
        } catch (e: IndexOutOfBoundsException) {
            false
        }

    fun downLeft(
        input: List<String>,
        line: Int,
        column: Int,
    ): Boolean =
        try {
            input[line + 1][column - 1] == 'M' &&
                input[line + 2][column - 2] == 'A' &&
                input[line + 3][column - 3] == 'S'
        } catch (e: StringIndexOutOfBoundsException) {
            false
        } catch (e: IndexOutOfBoundsException) {
            false
        }

    fun MSMS(
        input: List<String>,
        line: Int,
        column: Int,
    ): Boolean =
        try {
            input[line - 1][column - 1] == 'M' &&
                input[line - 1][column + 1] == 'S' &&
                input[line + 1][column - 1] == 'M' &&
                input[line + 1][column + 1] == 'S'
        } catch (e: StringIndexOutOfBoundsException) {
            false
        } catch (e: IndexOutOfBoundsException) {
            false
        }

    fun SMSM(
        input: List<String>,
        line: Int,
        column: Int,
    ): Boolean =
        try {
            input[line - 1][column - 1] == 'S' &&
                input[line - 1][column + 1] == 'M' &&
                input[line + 1][column - 1] == 'S' &&
                input[line + 1][column + 1] == 'M'
        } catch (e: StringIndexOutOfBoundsException) {
            false
        } catch (e: IndexOutOfBoundsException) {
            false
        }

    fun MMSS(
        input: List<String>,
        line: Int,
        column: Int,
    ): Boolean =
        try {
            input[line - 1][column - 1] == 'M' &&
                input[line - 1][column + 1] == 'M' &&
                input[line + 1][column - 1] == 'S' &&
                input[line + 1][column + 1] == 'S'
        } catch (e: StringIndexOutOfBoundsException) {
            false
        } catch (e: IndexOutOfBoundsException) {
            false
        }

    fun SSMM(
        input: List<String>,
        line: Int,
        column: Int,
    ): Boolean =
        try {
            input[line - 1][column - 1] == 'S' &&
                input[line - 1][column + 1] == 'S' &&
                input[line + 1][column - 1] == 'M' &&
                input[line + 1][column + 1] == 'M'
        } catch (e: StringIndexOutOfBoundsException) {
            false
        } catch (e: IndexOutOfBoundsException) {
            false
        }

    fun searchXMAS(
        char: Char,
        input: List<String>,
        line: Int,
        column: Int,
    ): Int {
        if (char != 'X') return 0

        var count = 0
        if (right(input, line, column)) count += 1
        if (left(input, line, column)) count += 1
        if (up(input, line, column)) count += 1
        if (down(input, line, column)) count += 1

        if (upRight(input, line, column)) count += 1
        if (upLeft(input, line, column)) count += 1
        if (downRight(input, line, column)) count += 1
        if (downLeft(input, line, column)) count += 1

        return count
    }

    fun searchXMAS2(
        char: Char,
        input: List<String>,
        line: Int,
        column: Int,
    ): Int {
        if (char != 'A') return 0

        var count = 0
        if (MSMS(input, line, column)) count += 1
        if (SMSM(input, line, column)) count += 1
        if (MMSS(input, line, column)) count += 1
        if (SSMM(input, line, column)) count += 1

        return count
    }

    fun List<String>.walk(
        search: (
            char: Char,
            input: List<String>,
            line: Int,
            column: Int,
        ) -> Int,
    ): Int {
        var count = 0
        for (line in this.indices) {
            for (column in this[line].indices) {
                count +=
                    search(
                        this[line][column],
                        this,
                        line,
                        column,
                    )
            }
        }
        return count
    }

    fun part1(input: List<String>): Int = input.walk(::searchXMAS)

    fun part2(input: List<String>): Int = input.walk(::searchXMAS2)

    fun test() {
        right(
            listOf("XMAS"),
            0,
            0,
        ).test(true)
        left(
            listOf("SAMX"),
            0,
            3,
        ).test(true)

        up(
            listOf(
                "S",
                "A",
                "M",
                "X",
            ),
            3,
            0,
        ).test(true)

        up(
            listOf(
                "A",
                "M",
                "X",
            ),
            3,
            0,
        ).test(false)

        down(
            listOf(
                "X",
                "M",
                "A",
                "S",
            ),
            0,
            0,
        ).test(true)

        upRight(
            listOf(
                "...S",
                "..A.",
                ".M..",
                "X...",
            ),
            3,
            0,
        ).test(true)

        upLeft(
            listOf(
                "S...",
                ".A..",
                "..M.",
                "...X",
            ),
            3,
            3,
        ).test(true)

        downRight(
            listOf(
                "X...",
                ".M..",
                "..A.",
                "...S",
            ),
            0,
            0,
        ).test(true)

        downLeft(
            listOf(
                "...X",
                "..M.",
                ".A..",
                "S...",
            ),
            0,
            3,
        ).test(true)

        MSMS(
            listOf(
                "M.S",
                ".A.",
                "M.S",
            ),
            1,
            1,
        ).test(true)

        SMSM(
            listOf(
                "S.M",
                ".A.",
                "S.M",
            ),
            1,
            1,
        ).test(true)

        MMSS(
            listOf(
                "M.M",
                ".A.",
                "S.S",
            ),
            1,
            1,
        ).test(true)

        SSMM(
            listOf(
                "S.S",
                ".A.",
                "M.M",
            ),
            1,
            1,
        ).test(true)

        part1(readInput("day04/test")).test(18)
    }

    test()

    val input = readInput("day04/input")
    part1(input).println()
    part2(input).println()
}
