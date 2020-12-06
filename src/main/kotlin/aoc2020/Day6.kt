package aoc2020

class Day6 {
    fun partOne(input: String) {
        val groupAnswerCount = splitInput(input)
            .map { groupAnswers ->
                groupAnswers
                    .split(" ")
                    .flatMap { answers -> answers.trim().split("").filter { s -> s.isNotEmpty() } }
                    .distinct()
            }
            .sumBy { arr -> arr.count() }

        println(groupAnswerCount)
    }

    fun partTwo(input: String) {

    }

    private fun splitInput(input: String): List<String> {
        return input
            .replace("\n\n", "^")
            .replace("\n", " ")
            .split("^")
    }
}