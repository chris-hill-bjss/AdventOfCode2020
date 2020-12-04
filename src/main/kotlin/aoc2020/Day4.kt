package aoc2020

class Day4 {
    fun partOne(input: String) {
        val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

        val passports = sanitiseInput(input)

        val validPassportCount = passports
            .map { passport ->
                passport
                    .trim()
                    .split(" ")
                    .associate { f -> f.split(":")[0] to f.split(":")[1] }
            }
            .count { fields -> requiredFields.all { f -> fields.containsKey(f) }}

        println(validPassportCount)
    }

    private fun sanitiseInput(input: String): List<String> {
        return input
            .replace("\n\n", "^")
            .replace("\n", " ")
            .split("^")
    }
}