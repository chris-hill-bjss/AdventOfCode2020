package aoc2020

import khttp.get

val solveAll = false;

fun main() {
    DayThree().partTwo(getInputForDay(3),
        setOf(
            1 to 1,
            3 to 1,
            5 to 1,
            7 to 1,
            1 to 2
        ))

    if (solveAll) { solveAll() }
}

fun solveAll() {
    DayOne().partOne(getInputForDay(1))
    DayOne().partTwo(getInputForDay(1))
    DayTwo().partOne(getInputForDay(2))
    DayTwo().partTwo(getInputForDay(2))
    DayThree().partOne(getInputForDay(3), 3 to 1)
}

fun getInputForDay(day: Int): String {
    return get(
        "https://adventofcode.com/2020/day/$day/input",
        mapOf("cookie" to "session=53616c7465645f5ffc76e44f95c7feefacc44f39b9e52b1abcddd2ce757e446aaf699c048ba840a9e59f0bbb0fa28d91")
    ).text
}