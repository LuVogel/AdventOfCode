package advent2024

import utils.BasePuzzle


class Day09 : BasePuzzle() {
    override fun puzzle1(filePath: String): Long {
        val input = getInput(filePath)
        val tmp = mutableListOf<Int>()
        val split = input[0].split("").filter { it.isNotBlank() or it.isNotEmpty() }.toMutableList()
        val dottedLine = createDotedLine1(split)
        val filledLine = fillLine1(dottedLine)
        return calculateSum1(filledLine)
    }

    override fun puzzle2(filePath: String): Long {
        val input = getInput(filePath)
        val split = input[0].split("").filter { it.isNotBlank() or it.isNotEmpty() }.toMutableList()
        println("split: $split")
        val (fileMap, emptyMap) = createDotedLine(split)
        println("fileMap: $fileMap")
        val sortedReversedFileMap = fileMap.toSortedMap(compareByDescending { it }).toMutableMap()
        println("sorted: $sortedReversedFileMap\nemptyMap: $emptyMap")
        val movedLine = moveFilesToFront(sortedReversedFileMap, emptyMap).toSortedMap()
        println("moved: $movedLine")
        return calculateSum(movedLine)
    }

    private fun moveFilesToFront(fileMap: MutableMap<Int, Pair<Int, Int>>, emptyMap: MutableMap<Int, Int>): MutableMap<Int, Pair<Int, Int>> {
        val movedFiles = mutableMapOf<Int, Pair<Int, Int>>()
        for (index in fileMap.keys) {
            if (fileMap[index] != null) {
                val currentLength = fileMap[index]!!.first
                val currentId = fileMap[index]!!.second
                val firstPlace = emptyMap.filter { it.value >= currentLength }
                if (firstPlace.isNotEmpty()) {
                    val smallestFound = firstPlace.minByOrNull { it.key }!!
                    if (smallestFound.key < index) {
                        movedFiles[smallestFound.key] = Pair(currentLength, currentId)
                        emptyMap.remove(smallestFound.key)
                        if (smallestFound.key - currentLength > 0) {
                            val diff = smallestFound.key- currentLength
                            val newIndex = currentLength + smallestFound.key
                            emptyMap[newIndex] = diff
                        }
                    } else {
                        movedFiles[index] = Pair(currentLength, currentId)
                    }
                } else {
                    movedFiles[index] = Pair(currentLength, currentId)
                }

            }
        }
        return movedFiles
    }

    private fun switch(dottedLine: MutableList<String>, newPlaces: List<Int>, currentId: Long, occurrences: List<Int>) {
        for (index in newPlaces) {
            dottedLine[index] = currentId.toString()
        }
        for (index in occurrences) {
            dottedLine[index] = "."
        }
    }

    private fun findNewPlaces(dottedLine: MutableList<String>, countOfId: Int, first: Int): List<Int> {
        val emptyIndexes = dottedLine.mapIndexedNotNull {index, value ->
            if (value == ".") index else null
        }
        var foundSequence = mutableListOf<Int>()
        for (i in emptyIndexes.indices) {
            if (foundSequence.isEmpty()) {
                foundSequence.add(emptyIndexes[i])
                if (foundSequence.size == countOfId) {
                    return foundSequence
                }
            } else if (emptyIndexes[i] < first) {
                val currentLast = foundSequence.last()
                val shouldBeCurrent  =currentLast + 1
                if (emptyIndexes[i] ==  shouldBeCurrent) {
                    foundSequence.add(emptyIndexes[i])
                    if (foundSequence.size == countOfId) {
                        return foundSequence
                    }
                } else {
                    foundSequence.clear()
                    foundSequence.add(emptyIndexes[i])
                    if (foundSequence.size == countOfId) {
                        return foundSequence
                    }
                }
            } else {
                return emptyList()
            }
        }
        return emptyList()

    }

    private fun calculateSum(filledLine: MutableMap<Int, Pair<Int, Int>>): Long {
        var sum = 0L
        for (index in filledLine.keys) {
            val currentId = filledLine[index]!!.second
            val currentLength = filledLine[index]!!.first
            for (i in index .. index+currentLength) {
                sum += i.toLong() * currentId.toLong()
            }
        }
        return sum
    }

    private fun fillLine(split: MutableList<String>): MutableList<String> {
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
    private fun createDotedLine(split: List<String>): Pair<MutableMap<Int, Pair<Int, Int>>, MutableMap<Int, Int>> {
        val sb = mutableMapOf<Int, Pair<Int,Int>>()
        val emptyMap = mutableMapOf<Int, Int>()
        var empty = false
        var index = 0
        var counter = 0
        for (s in split) {
            if (empty) {
                empty = false
                emptyMap[index] = s.toInt()
            } else {
                empty = true
                sb[index] = Pair(s.toInt(), counter)
                counter++
            }
            index += s.toInt()
        }
        return Pair(sb, emptyMap)
    }

    private fun fillLine1(split: MutableList<String>): MutableList<String> {
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
    private fun createDotedLine1(split: List<String>): MutableList<String> {
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
    private fun calculateSum1(filledLine: List<String>): Long {
        val tmp = filledLine.mapNotNull { it.toIntOrNull() }
        var sum = 0L
        for (i in tmp.indices) {
            sum += i * tmp[i]
        }
        return sum
    }
}