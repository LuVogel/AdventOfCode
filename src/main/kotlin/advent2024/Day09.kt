package advent2024

import utils.BasePuzzle


class Day09 : BasePuzzle() {
    override fun puzzle1(filePath: String): Long {
        val input = getInput(filePath)
        val tmp = mutableListOf<Int>()
        val split = input[0].split("").filter { it.isNotBlank() or it.isNotEmpty() }.toMutableList()
        val dottedLine = createDotedLine(split)
        val filledLine = fillLine(dottedLine)
        return calculateSum(filledLine)
    }

    override fun puzzle2(filePath: String): Any {
        TODO("Not yet implemented")
    }

    private fun calculateSum(filledLine: List<String>): Long {
        val tmp = filledLine.mapNotNull { it.toIntOrNull() }
        var sum = 0L
        for (i in tmp.indices) {
            sum += i * tmp[i]
        }
        return sum
    }

    private fun fillLine(split: MutableList<String>): MutableList<String> {
        var lastFoundEmpty = 0
        while (split.contains(".")) {
            val lastIndex = split.indexOfLast { it != "." }
            val last = split.removeAt(lastIndex)
            val firstEmptyIndex = split.indexOfFirst { it == "." }
            if (firstEmptyIndex != -1) {
                split[firstEmptyIndex] = last
            } else {
                split.add(last)
            }
        }
        return split
    }
    private fun createDotedLine(split: List<String>): MutableList<String> {
        val sb = mutableListOf<String>()
        var empty = false
        var counter = 0
        for (s in split) {
            var tmp = ""
            if (empty) {
                empty = false
                tmp = "."
            } else {
                tmp = counter.toString()
                counter++
                empty = true
            }
            for (i in 0 until s.toInt()) {
                sb.add(tmp)
            }
        }
        return sb
    }
}