package aoc2020

class DayThree {
    fun partOne(input: String, slope: Pair<Int, Int>) {
        val map = inputToMap(input)

        println(solveForSlope(map, slope))
    }

    fun partTwo(input: String, slopes: Set<Pair<Int, Int>>) {
        val map = inputToMap(input)

        val collisions = slopes
            .map { slope -> solveForSlope(map, slope) }
            .reduce{ acc, i -> acc * i }

        println(collisions)
    }

    private fun inputToMap(input: String): List<String> {
        return input
            .split("\n")
            .map { s -> s.trim() }
            .filter { s -> s.isNotEmpty() }
    }

    private fun solveForSlope(map: List<String>, slope: Pair<Int, Int>): Long {
        var position = Pair(0, 0)
        var collisions = 0L
        while (position.second < map.count()) {
            var row = map[position.second]

            while (position.first >= row.length) {
                row += row
            }

            when (row[position.first]) {
                '#' -> collisions++
            }

            position = move(position, slope)
        }

        return collisions
    }

    private fun move(currentPosition: Pair<Int, Int>, slope: Pair<Int, Int>): Pair<Int, Int> {
        return Pair(currentPosition.first + slope.first, currentPosition.second + slope.second)
    }
}