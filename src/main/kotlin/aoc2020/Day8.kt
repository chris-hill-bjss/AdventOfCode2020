package aoc2020

class Day8 {
    fun partOne(input: String) {
        val programme = splitInput(input)

        println(execute(programme))
    }

    fun partTwo(input: String) {
        val programme = splitInput(input)

        val result = programme
            .mapIndexed { index, instruction -> index to instruction }
            .filter { (_, instruction) -> instruction.first == "nop" || instruction.first == "jmp" }
            .map { (pos, instruction) ->
                val newProgramme = programme.toTypedArray()

                newProgramme[pos] = when(instruction.first) {
                    "nop" -> "jmp" to instruction.second
                    else -> "nop" to instruction.second
                }

                execute(newProgramme.toList())
            }
            .first { it.startsWith("complete") }

        println(result)
    }

    private fun execute(programme: List<Pair<String, Int>>):String {
        val seen = HashSet<Int>()
        var pos = 0
        var acc = 0

        while(pos < programme.size) {
            val instruction = programme[pos]

            if (seen.contains(pos)) {
                return "loopState: $acc:$instruction"
            } else {
                seen.add(pos)
            }

            when(instruction.first) {
                "acc" -> {
                    acc += instruction.second
                    pos++
                }
                "jmp" -> pos += instruction.second
                "nop" -> pos++
            }
        }

        return "complete:$acc"
    }

    private fun splitInput(input: String): List<Pair<String, Int>> {
        return input
            .split("\n")
            .filter { it.isNotEmpty() }
            .map { it.split(" ")[0] to it.split(" ")[1].toInt() }
    }
}