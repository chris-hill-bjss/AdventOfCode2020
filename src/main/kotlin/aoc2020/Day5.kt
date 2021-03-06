package aoc2020

class Day5 {
    fun partOne(input: String) {
        val seatLocations = splitInput(input)
            .map { pass -> pass to getSeatLocation(pass) }
            .maxBy { (_, info) -> info.second }

        println(seatLocations)
    }

    fun partTwo(input: String) {
        val seatLocations = splitInput(input)
            .map { pass -> pass to getSeatLocation(pass) }
            .map { (_, info) -> info.second }
            .sorted()

        val mySeatId = (seatLocations.first()..seatLocations.last()).first { id -> id !in seatLocations }

        println(mySeatId);
    }

    private fun getSeatLocation(pass: String): Pair<Pair<Int, Int>, Int> {
        var rows = IntArray(128) { it }
        var cols = IntArray(8) { it }

        for (c in pass) {
            when(c) {
                'F' -> rows = IntArray(rows.size / 2) { it + rows[0] }
                'B' -> rows = IntArray(rows.size / 2) { it + rows[rows.size / 2] }
                'L' -> cols = IntArray(cols.size / 2) { it + cols[0] }
                'R' -> cols = IntArray(cols.size / 2) { it + cols[cols.size / 2] }
            }
        }

        return rows[0] to cols[0] to (rows[0] * 8 + cols[0])
    }

    private fun splitInput(input: String): List<String> {
        return input
            .split("\n")
            .filter { s -> s.isNotEmpty() }
            .map { s -> s.trim() }
    }
}