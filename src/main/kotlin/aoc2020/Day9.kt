package aoc2020

import java.math.BigInteger

class Day9 {
    fun partOne(input: String, preambleLength: Int) {
        val sequence = splitInput(input)

        var pos = preambleLength

        for(value in sequence.drop(preambleLength)){
            val values = sequence.sliceArray(pos-preambleLength until pos)

            val validValues = values
                .flatMap { v -> values.filter { it != v }.map { it + v } }


            if (!validValues.contains(value)) println("$value:$validValues")
            pos++
        }
    }

    fun partTwo(input: String) {

    }

    private fun splitInput(input: String): Array<Long> {
        return input
            .split("\n")
            .filter { it.isNotEmpty() }
            .map { it.toLong() }
            .toTypedArray()
    }
}