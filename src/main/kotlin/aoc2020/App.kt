package aoc2020

import khttp.get

fun main(args: Array<String>) {
    Day6().partOne(getInputForDay(6))

    if (args.any() && args[0] == "solveAll") { solveAll() }
}

fun solveAll() {
    Day1().partOne(getInputForDay(1))
    Day1().partTwo(getInputForDay(1))
    Day2().partOne(getInputForDay(2))
    Day2().partTwo(getInputForDay(2))
    Day3().partOne(getInputForDay(3), 3 to 1)
    Day3().partTwo(getInputForDay(3),
        setOf(
            1 to 1,
            3 to 1,
            5 to 1,
            7 to 1,
            1 to 2
        ))
    Day4().partOne(getInputForDay(4))
    Day4().partTwo(getInputForDay(4))
    Day5().partOne(getInputForDay(5))
    Day5().partTwo(getInputForDay(5))
}

fun getInputForDay(day: Int): String {
    return get(
        "https://adventofcode.com/2020/day/$day/input",
        mapOf("cookie" to "session=53616c7465645f5ffc76e44f95c7feefacc44f39b9e52b1abcddd2ce757e446aaf699c048ba840a9e59f0bbb0fa28d91")
    ).text
}