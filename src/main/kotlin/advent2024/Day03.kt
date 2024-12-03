package advent2024

import utils.BasePuzzle

class Day03 : BasePuzzle() {


    override fun puzzle1(filePath: String): Any {
        val input = getInput(filePath)
        val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
        var sum = 0
        for (line in input) {
            var matches = regex.findAll(line)
            for (match in matches) {
                val temp = match.value.substringAfterLast("(").substringBeforeLast(")").split(",")
                sum += temp[0].toInt() * temp[1].toInt()
            }
        }
        return sum
    }

    override fun puzzle2(filePath: String): Any {
        val input = getInput(filePath)
        val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)")
        var sum = 0
        var enabled = true
        for (line in input) {
            var matches = regex.findAll(line)
            for (match in matches) {
                if (match.value == "don't()") {
                    enabled = false
                } else if (match.value == "do()") {
                    enabled = true
                } else {
                    if (enabled) {
                        val temp = match.value.substringAfterLast("(").substringBeforeLast(")").split(",")
                        sum += temp[0].toInt() * temp[1].toInt()
                    }
                }

            }
        }
        return sum
    }
}