package aoc2020

class DayOne {

    fun partOne(input: String) {
        val values = splitInput(input);

        values.forEach { a ->
            values
                .filter { b -> b != a }
                .forEach { b ->
                    when (a + b) {
                        2020 -> {
                            println("$a, $b, sum: ${a * b}")
                            return
                        }
                    }
                }
            }
    }

    fun partTwo(input: String) {
        val values = splitInput(input);

        values.forEach { a ->
            values
                .filter { b -> b != a }
                .forEach { b ->
                    values
                        .filter { c -> c != a && c != b }
                        .forEach { c ->
                            when (a + b + c) {
                                2020 -> {
                                    println("$a, $b, $c, sum: ${a * b * c}")
                                    return
                                }
                            }
                        }
                }
        }
    }

    private fun splitInput(input: String): List<Int> {
        return input
            .split("\n")
            .filter { s-> s.isNotEmpty() }
            .map { s -> s.toInt() }
    }
}