
package aoc2020

import khttp.get

fun main() {
    DayOne().partOne(getInputForDay(1))
    DayOne().partTwo(getInputForDay(1))
    DayTwo().partOne(getInputForDay(2))
    DayTwo().partTwo(getInputForDay(2))
}

fun getInputForDay(day: Int): String {
    return get(
        "https://adventofcode.com/2020/day/$day/input",
        mapOf("cookie" to "session=53616c7465645f5ffc76e44f95c7feefacc44f39b9e52b1abcddd2ce757e446aaf699c048ba840a9e59f0bbb0fa28d91")
    ).text
}