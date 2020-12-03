package aoc2020

class Day1 {

    fun partOne(input: String) {
        val values = splitInput(input)

        val (a, b) = values.flatMap { a ->
            values
                .filter { b -> b != a }
                .filter { b -> a + b == 2020}
                .map { b -> a to b }
            }
            .first()

        println("$a, $b, sum: ${a * b}")
    }

    fun partTwo(input: String) {
        val values = splitInput(input)

        val (a, b, c) = values.flatMap { a ->
            values
                .filter { b -> b != a }
                .flatMap { b ->
                    values
                        .filter { c -> c != a && c != a }
                        .filter { c -> a + b + c == 2020 }
                        .map { c -> Triple(a, b, c) }
                }
            }
            .first()

        println("$a, $b, $c, sum: ${a * b * c}")
    }

    private fun splitInput(input: String): List<Int> {
        return input
            .split("\n")
            .filter { s-> s.isNotEmpty() }
            .map { s -> s.toInt() }
    }
}