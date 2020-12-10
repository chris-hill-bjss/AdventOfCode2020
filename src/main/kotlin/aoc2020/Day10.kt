package aoc2020

class Day10 {
    fun partOne(input: String) {
        val numbers = splitInput(input)
        
        val differences = numbers
            .mapIndexed{ i, v ->
                when (i < numbers.size-1) {
                    true -> numbers[i+1] - v
                    else -> 0
                }
            }
            .filter { it > 0 }
            .groupBy { it }
            .map { it.key to it.value.count() }

        println(differences)
        println(differences.first().second * differences.last().second)
    }

    fun partTwo(input: String) {

    }

    private fun splitInput(input: String): Array<Int> {
        var numbers = input
            .split("\n")
            .filter { it.isNotEmpty() }
            .map { it.toInt() }
            .toMutableList()

        numbers.add(0, 0)
        numbers.add(numbers.max()!! + 3)

        return numbers.sorted().toTypedArray()
    }
}