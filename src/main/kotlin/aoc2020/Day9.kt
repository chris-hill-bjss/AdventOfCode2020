package aoc2020

class Day9 {
    fun partOne(input: String, preambleLength: Int) {
        val sequence = splitInput(input)

        println(getInvalidNumber(sequence, preambleLength))
    }

    fun partTwo(input: String, preambleLength: Int) {
        val sequence = splitInput(input)

        val invalidNumber = getInvalidNumber(sequence, preambleLength)

        val (sum, range) = sequence
            .mapIndexed { i, value ->
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
        var pos = preambleLength

        for(value in sequence.drop(preambleLength)){
            val values = sequence.sliceArray(pos-preambleLength until pos)

            val validValues = values
                .flatMap { v -> values.filter { it != v }.map { it + v } }

            if (!validValues.contains(value)) {
                return value
            }

            pos++
        }

        throw IllegalStateException("No invalid number found!")
    }

    private fun splitInput(input: String): Array<Long> {
        return input
            .split("\n")
            .filter { it.isNotEmpty() }
            .map { it.toLong() }
            .toTypedArray()
    }
}