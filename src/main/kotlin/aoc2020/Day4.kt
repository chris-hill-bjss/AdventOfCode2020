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

    private class Validator(validation: (String) -> Boolean) {
        private val validation = validation

        fun validate(value: String): Boolean {
            return validation.invoke(value)
        }
    }

    fun partTwo(input: String) {
        val requiredFields = mapOf(
            "byr" to Validator { s -> s.toInt() in 1920..2002 },
            "iyr" to Validator { s -> s.toInt() in 2010..2020 },
            "eyr" to Validator { s -> s.toInt() in 2020..2030 },
            "hgt" to Validator { s -> validateHeight(s) },
            "hcl" to Validator { s -> s.matches(Regex("#[0-9a-f]{6}")) },
            "ecl" to Validator { s -> s.matches(Regex("amb|blu|brn|gry|grn|hzl|oth")) },
            "pid" to Validator { s -> s.matches(Regex("[0-9]{9}")) })

        val passports = sanitiseInput(input)

        val passportsWithRequiredFields = passports
            .map { passport ->
                passport
                    .trim()
                    .split(" ")
                    .associate { f -> f.split(":")[0] to f.split(":")[1] }
                    .filter { (name, _) -> name != "cid" }
            }
            .filter { fields -> requiredFields.all { (name, _) -> fields.containsKey(name) }}

        val validPassports = passportsWithRequiredFields
            .filter { fields -> fields.all { (name, value) -> requiredFields[name]!!.validate(value)}}

        println(validPassports.count())
    }

    private fun validateHeight(input: String): Boolean {
        val match = Regex("(\\d+)(\\w{2})").find(input)
        if (match != null) {
            val (value, unit) = match.destructured
            return when (unit) {
                "cm" -> value.toInt() in 150..193
                "in" -> value.toInt() in 59..76
                else -> false
            }
        }

        return false
    }

    private fun sanitiseInput(input: String): List<String> {
        return input
            .replace("\n\n", "^")
            .replace("\n", " ")
            .split("^")
    }
}