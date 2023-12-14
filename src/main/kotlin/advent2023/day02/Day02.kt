package advent2023.day02

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


class Day02 {

    fun solvePart1Day02(filePath: String): Int {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())

        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var line: String?
                var sum = 0
                while (bufferedReader.readLine().also { line = it } != null) {
                    var resultOfSplit: MutableMap<String, List<String>> = splitLine(line)
                    var pots = resultOfSplit["pots"]
                    var toAdd = true
                    for (pot in pots!!) {
                        var resultOfPot: Map<String, Int> = extractPot(pot)
                        var countRed = resultOfPot["red"]
                        var countBlue = resultOfPot["blue"]
                        var countGreen = resultOfPot["green"]
                        if (countRed != null) {
                            if (countRed > 12) {
                                toAdd = false

                            }
                        }
                        if (countBlue != null) {
                            if (countBlue > 14) {
                                toAdd = false
                            }
                        }
                        if (countGreen != null) {
                            if (countGreen > 13) {
                                toAdd = false
                            }
                        }
                    }
                    if (toAdd) {
                        sum += resultOfSplit["gameId"]!![0].toInt()
                    }
                }
                return sum
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    fun solvePart2Day02(filePath: String): Int {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())

        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var line: String?
                var sum = 0
                while (bufferedReader.readLine().also { line = it } != null) {
                    var resultOfSplit: MutableMap<String, List<String>> = splitLine(line)
                    var pots = resultOfSplit["pots"]
                    var colorMap : MutableMap<String, Int> = mutableMapOf("red" to Int.MIN_VALUE, "blue" to Int.MIN_VALUE, "green" to Int.MIN_VALUE)
                    for (pot in pots!!) {
                        var resultOfPot: Map<String, Int> = extractPot(pot)
                        var countRed = resultOfPot["red"]
                        var countBlue = resultOfPot["blue"]
                        var countGreen = resultOfPot["green"]
                        if (countRed != null) {
                            if (countRed > colorMap["red"]!!) {
                                colorMap["red"] = countRed
                            }
                        }
                        if (countBlue != null) {
                            if (countBlue > colorMap["blue"]!!) {
                                colorMap["blue"] = countBlue
                            }
                        }
                        if (countGreen != null) {
                            if (countGreen > colorMap["green"]!!) {
                                colorMap["green"] = countGreen
                            }
                        }
                    }
                   // var cRed = if (colorMap["red"] != null) colorMap["red"]!!.toInt() else 1
                    var cRed = colorMap["red"] ?: 1
                    var cBlue = colorMap["blue"] ?: 1
                    var cGreen = colorMap["green"] ?: 1

                    sum += (cRed * cBlue * cGreen).also { print(it) }
                }
                return sum
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    private fun extractPot(potLine: String?): Map<String, Int> {
        var split = potLine!!.split(", ")
        var map : MutableMap<String, Int> = mutableMapOf<String, Int>()
        for (colorCount : String in split) {
            var tmp = colorCount.split(" ")
            var id = tmp[0].toInt()
            var color : String = tmp[1]
            map[color] = id
        }
        return map
    }

    private fun splitLine(line: String?): MutableMap<String, List<String>> {
        val firstSplit = line!!.split(": ")
        val gameId : List<String> = listOf(firstSplit[0].split(" ")[1])
        val pots : List<String> = firstSplit[1].split("; ")
        return mutableMapOf("gameId" to gameId, "pots" to pots)

    }


}