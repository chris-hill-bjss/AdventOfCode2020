package aoc2020

class Day7 {
    fun partOne(input: String) {
        val rules = splitInput(input)

        val filteredRules = rules.filter { (rule, _) -> ruleContainsGold(rule, rules) }

        println(filteredRules.count())
    }

    fun partTwo(input: String) {
        val rules = splitInput(input)

        var totalBags = innerBagCount("shiny gold", rules);

        println(totalBags)
    }

    private fun ruleContainsGold(rule: String, rules: Map<String, List<Pair<Int, String>>>): Boolean {
        return rules[rule]
            ?.let { ruleMembers -> ruleMembers.any{ (_, member) -> member.contains("shiny gold") || ruleContainsGold(member, rules) } }
            ?:false
    }

    private fun innerBagCount(rule: String, rules: Map<String, List<Pair<Int, String>>>): Int {
        return rules[rule]
            ?.let { ruleMembers -> ruleMembers.sumBy { (times, member) -> times + (times * innerBagCount(member, rules)) } }
            ?:0
    }
    private fun splitInput(input: String): Map<String, List<Pair<Int, String>>> {
        return input
            .split("\n")
            .filter { s -> s.isNotEmpty() }
            .map { rule ->
                val matches = Regex("((?:\\d\\s)?(?:(?:\\w+\\s){2}))").findAll(rule)
                    .filter { match -> !match.value.startsWith("bags") }
                    .map { match -> match.value.trim() }

                matches.first() to matches.drop(1)
                    .filter { member -> !member.startsWith("no") }
                    .map { member -> member.take(1).toInt() to member.drop(2) }
                    .toList()
            }
            .associateBy({ (rule, _) -> rule }, { (_, members) -> members })
    }
}