package aoc2020

class DayTwo {
    fun partOne(input: String) {
        val tests = splitInput(input) {
            s -> Rule(s.split(":")[0]) to s.split(":")[1]
        }

        val validPasswords = tests.count { test ->
            val rule = test.key;
            rule.isValid(test.value)
        }

        println(validPasswords)
    }

    fun partTwo(input: String) {
        val tests = splitInput(input) {
            s -> Rule2(s.split(":")[0].trim()) to s.split(":")[1].trim()
        }

        val validPasswords = tests.count { test ->
            val rule = test.key;
            rule.isValid(test.value)
        }

        println(validPasswords)
    }

    private fun <K> splitInput(input: String, transform: (String) -> Pair<K, String>): Map<K, String> {
        return input.split("\n")
            .filter { s-> s.isNotEmpty() }
            .associate { s -> transform.invoke(s) }
    }

    private class Rule(spec: String) {
        val min = spec.split(" ")[0].split("-")[0].toInt()
        val max = spec.split(" ")[0].split("-")[1].toInt()
        val letter = spec.split(" ")[1][0]

        fun isValid(value: String): Boolean {
            val letterCount = value.filter { c -> c == letter }.length

            return letterCount in min..max;
        }
    }

    private class Rule2(spec: String) {
        val posOne = spec.split(" ")[0].split("-")[0].toInt() - 1
        val posTwo = spec.split(" ")[0].split("-")[1].toInt() - 1
        val letter = spec.split(" ")[1][0]

        fun isValid(value: String): Boolean {
            val valOne = value[posOne]
            val valTwo = value[posTwo]
            return (valOne != valTwo) && (valOne == letter || valTwo == letter)
        }
    }
}