package aoc2020

class DayTwo {
    fun partOne(input: String) {
        parseInputAndExecute(input) {
            s -> FrequencyRule(s.split(":")[0]) to s.split(":")[1]
        }
    }

    fun partTwo(input: String) {
        parseInputAndExecute(input) {
            s -> PositionalRule(s.split(":")[0].trim()) to s.split(":")[1].trim()
        }
    }

    private fun parseInputAndExecute(input: String, transform: (String) -> Pair<Rule, String>) {
        val tests = splitInput(input) { s -> transform.invoke(s) }

        println(countValidPasswords(tests))
    }

    private fun splitInput(input: String, transform: (String) -> Pair<Rule, String>): Map<Rule, String> {
        return input.split("\n")
            .filter { s-> s.isNotEmpty() }
            .associate { s -> transform.invoke(s) }
    }
    private fun countValidPasswords(tests: Map<Rule, String>): Int {
        return tests.count { test ->
            val rule = test.key;
            rule.isValid(test.value)
        }
    }

    private abstract class Rule(spec: String) {
        val lower = spec.split(" ")[0].split("-")[0].toInt()
        val upper = spec.split(" ")[0].split("-")[1].toInt()
        val letter = spec.split(" ")[1][0]

        abstract fun isValid(value: String): Boolean
    }

    private class FrequencyRule(spec: String) : Rule(spec) {
        override fun isValid(value: String): Boolean {
            val letterCount = value.filter { c -> c == letter }.length

            return letterCount in lower..upper;
        }
    }

    private class PositionalRule(spec: String) : Rule(spec) {
        override fun isValid(value: String): Boolean {
            val valOne = value[lower]
            val valTwo = value[upper]

            return (valOne != valTwo) && (valOne == letter || valTwo == letter)
        }
    }
}