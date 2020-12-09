package aoc2020

class Day9 {
    fun partOne(input: String, preambleLength: Int) {
        println(getInvalidNumber(splitInput(input), preambleLength))
    }

    fun partTwo(input: String, preambleLength: Int) {
        val sequence = splitInput(input)
        val invalidNumber = getInvalidNumber(sequence, preambleLength)
        val (sum, range) = sequence
            .mapIndexed { i, _ ->
                var acc = 0L
                var pos = i

                for(number in sequence.drop(i)) {
                    acc += number
                    if (acc >= invalidNumber)
                        break
                    pos++
                }

                acc to sequence.sliceArray(i..pos).toList()
            }
            .first { (sum, _) -> sum == invalidNumber }

        println("$sum:$range:${range.min()!! + range.max()!!}")
    }

    private fun getInvalidNumber(sequence: Array<Long>, preambleLength: Int):Long {
        val (number, _) = sequence
            .drop(preambleLength)
            .mapIndexed { i, target -> target to sequence.sliceArray(i until i+preambleLength).toList() }
            .map { (target, range) -> target to range.flatMap { v -> range.filter { it != v }.map { it + v } }.distinct() }
            .first { (target, values) -> !values.contains(target) }

        return number
    }

    private fun splitInput(input: String): Array<Long> {
        return input
            .split("\n")
            .filter { it.isNotEmpty() }
            .map { it.toLong() }
            .toTypedArray()
    }
}