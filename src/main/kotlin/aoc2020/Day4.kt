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

    fun partTwo(input: String) {
        val requiredFields = mapOf(
            "byr" to { s: String -> s.toInt() in 1920..2002 },
            "iyr" to { s: String -> s.toInt() in 2010..2020 },
            "eyr" to { s: String -> s.toInt() in 2020..2030 },
            "hgt" to { s: String -> validateHeight(s) },
            "hcl" to { s: String -> s.matches(Regex("#[0-9a-f]{6}")) },
            "ecl" to { s: String -> s.matches(Regex("amb|blu|brn|gry|grn|hzl|oth")) },
            "pid" to { s: String -> s.matches(Regex("[0-9]{9}")) })

        val passports = sanitiseInput(input)

        val validPassportsCount = passports
            .map { passport ->
                passport
                    .trim()
                    .split(" ")
                    .associate { f -> f.split(":")[0] to f.split(":")[1] }
                    .filter { (name, _) -> name != "cid" }
            }
            .filter { fields -> requiredFields.all { (name, _) -> fields.containsKey(name) }}
            .count { fields -> fields.all { (name, value) -> requiredFields[name]!!.invoke(value)}}

        println(validPassportsCount)
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