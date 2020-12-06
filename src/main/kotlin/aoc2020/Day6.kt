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
        val parsedInput = splitInput(input)
        val groupDuplicateAnswer = parsedInput
            .map { groupAnswers -> groupAnswers.split(" ").filter { s -> s.isNotEmpty() }}
            .map { personAnswers ->
                personAnswers.count() to personAnswers.map { answers ->
                    answers.trim().split("").filter { s -> s.isNotEmpty() }
                }
            }
            .map { (members, answers) ->
                answers
                    .flatMap { person -> person.map { a -> a } }
                    .groupBy { a -> a }
                    .filter { grp -> grp.value.count() == members }
                    .count()
            }
            .sum()

        println(groupDuplicateAnswer)
    }

    private fun splitInput(input: String): List<String> {
        return input
            .replace("\n\n", "^")
            .replace("\n", " ")
            .split("^")
    }
}