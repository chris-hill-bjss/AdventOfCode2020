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

        println(differences.first().second * differences.last().second)
    }

    fun partTwo(input: String) {
        val numbers = splitInput(input)

        var last = 0
        var acc = 0
        var currentCombinations = 1
        var totalCombinations = 1L

        for(number in numbers) {
            val diff = number - last
            if (diff in 1..2) {
                currentCombinations += acc
                acc++
            }
            else if (acc != 0) {
                totalCombinations *= currentCombinations
                acc = 0;
                currentCombinations = 1
            }

            last = number
        }

        println(totalCombinations)
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