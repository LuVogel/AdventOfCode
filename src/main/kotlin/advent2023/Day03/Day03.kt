package advent2023.Day03

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


class Day03 {

    fun solvePart1Day03(filePath: String): Int {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())

        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var line: String?
                val matrix : MutableList<MutableList<String>> = mutableListOf()
                while (bufferedReader.readLine().also { line = it } != null) {
                    val tmp = line!!.split("").toMutableList()
                    tmp.removeAt(0)
                    tmp.removeAt(tmp.lastIndex)
                    matrix.add(tmp)
                }
                return checkMatrixPart1(matrix)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    private fun checkMatrixPart1(matrix: MutableList<MutableList<String>>): Int {
        var sum = 0
        println("------------")
        println("${matrix.lastIndex}, ${matrix[0].lastIndex}")
        println("--------------------------")
        for (yIndex in 0 until matrix.lastIndex+1) {
            var foundNumber  = ""
            var foundXCoords : MutableList<Int> = mutableListOf()
            var foundYCoords : MutableList<Int> = mutableListOf()
            for (xIndex in 0 until matrix[yIndex].lastIndex+1) {
                val currentSymbol = matrix[yIndex][xIndex]
                if (currentSymbol.toIntOrNull() != null) {
                    foundNumber += currentSymbol
                    foundXCoords.add(xIndex).also { println("add x: $xIndex") }
                    foundYCoords.add(yIndex).also { println("add y: $yIndex") }
                } else if (foundNumber != "") {
                    if (checkNumber(foundXCoords, foundYCoords, matrix)) {
                        sum += foundNumber.toInt()
                    }
                    foundNumber = ""
                    foundXCoords.clear()
                    foundYCoords.clear()
                }

            }
        }
        return sum

    }

    private fun checkNumber(foundXCoords: MutableList<Int>, foundYCoords: MutableList<Int>, matrix: MutableList<MutableList<String>>): Boolean {
        for (i in 0 until foundYCoords.lastIndex+1) {
            val xAxis = foundXCoords[i]
            val yAxis = foundYCoords[i]
            println("x: $xAxis, y: $yAxis, matrix: [${matrix.lastIndex}][${matrix[0].lastIndex}]")
            if (yAxis == 0 && xAxis == 0) {
                //check top left corner
                if (matrix[yAxis][xAxis + 1].toIntOrNull() == null && matrix[yAxis][xAxis + 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis + 1][xAxis + 1].toIntOrNull() == null && matrix[yAxis + 1][xAxis + 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis + 1][xAxis].toIntOrNull() == null && matrix[yAxis + 1][xAxis] != ".") {
                    //found
                    return true
                }
            } else if (yAxis == matrix.lastIndex && xAxis == 0) {
                //check down left corner
                if (matrix[yAxis][xAxis + 1].toIntOrNull() == null && matrix[yAxis][xAxis + 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis - 1][xAxis + 1].toIntOrNull() == null && matrix[yAxis - 1][xAxis + 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis - 1][xAxis].toIntOrNull() == null && matrix[yAxis - 1][xAxis] != ".") {
                    //found
                    return true
                }
            } else if (yAxis == matrix.lastIndex && xAxis == matrix[0].lastIndex) {
                //check down right corner
                if (matrix[yAxis][xAxis - 1].toIntOrNull() == null && matrix[yAxis][xAxis - 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis - 1][xAxis - 1].toIntOrNull() == null && matrix[yAxis - 1][xAxis - 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis - 1][xAxis].toIntOrNull() == null && matrix[yAxis - 1][xAxis] != ".") {
                    //found
                    return true
                }
            } else if (yAxis == 0 && xAxis == matrix[0].lastIndex) {
                //check top right corner
                if (matrix[yAxis][xAxis - 1].toIntOrNull() == null && matrix[yAxis][xAxis - 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis + 1][xAxis - 1].toIntOrNull() == null && matrix[yAxis + 1][xAxis - 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis + 1][xAxis].toIntOrNull() == null && matrix[yAxis + 1][xAxis] != ".") {
                    //found
                    return true
                }
            } else if (yAxis == 0) {
                //check top side without corner
                if (matrix[yAxis][xAxis + 1].toIntOrNull() == null && matrix[yAxis][xAxis + 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis + 1][xAxis + 1].toIntOrNull() == null && matrix[yAxis + 1][xAxis + 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis + 1][xAxis].toIntOrNull() == null && matrix[yAxis + 1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis + 1][xAxis + 1].toIntOrNull() == null && matrix[yAxis + 1][xAxis + 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis][xAxis - 1].toIntOrNull() == null && matrix[yAxis][xAxis - 1] != ".") {
                    //found
                    return true
                }
            } else if (yAxis == matrix.lastIndex) {
                //check bottom side without corner
                if (matrix[yAxis][xAxis + 1].toIntOrNull() == null && matrix[yAxis][xAxis + 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis - 1][xAxis + 1].toIntOrNull() == null && matrix[yAxis - 1][xAxis + 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis - 1][xAxis].toIntOrNull() == null && matrix[yAxis - 1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis - 1][xAxis - 1].toIntOrNull() == null && matrix[yAxis - 1][xAxis - 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis][xAxis - 1].toIntOrNull() == null && matrix[yAxis][xAxis - 1] != ".") {
                    //found
                    return true
                }
            } else if (xAxis == 0) {
                //check left side without corner
                if (matrix[yAxis - 1][xAxis].toIntOrNull() == null && matrix[yAxis - 1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis + 1][xAxis].toIntOrNull() == null && matrix[yAxis + 1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis - 1][xAxis + 1].toIntOrNull() == null && matrix[yAxis - 1][xAxis + 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis + 1][xAxis + 1].toIntOrNull() == null && matrix[yAxis + 1][xAxis + 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis][xAxis + 1].toIntOrNull() == null && matrix[yAxis][xAxis + 1] != ".") {
                    //found
                    return true
                }
            } else if (xAxis == matrix[0].lastIndex) {
                //check right side without corner
                if (matrix[yAxis - 1][xAxis].toIntOrNull() == null && matrix[yAxis - 1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis + 1][xAxis].toIntOrNull() == null && matrix[yAxis + 1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis - 1][xAxis - 1].toIntOrNull() == null && matrix[yAxis - 1][xAxis - 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis + 1][xAxis - 1].toIntOrNull() == null && matrix[yAxis + 1][xAxis - 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis][xAxis - 1].toIntOrNull() == null && matrix[yAxis][xAxis - 1] != ".") {
                    //found
                    return true
                }
            } else {
                //not on boarder
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis - 1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis + 1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis - 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis][xAxis+1].toIntOrNull() == null && matrix[yAxis][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis+1].toIntOrNull() == null && matrix[yAxis+1][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis-1][xAxis+1].toIntOrNull() == null && matrix[yAxis-1][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis-1].toIntOrNull() == null && matrix[yAxis + 1][xAxis - 1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis-1][xAxis-1].toIntOrNull() == null && matrix[yAxis - 1][xAxis - 1] != ".") {
                    //found
                    return true
                }
            }
        }

        return false

    }



    fun solvePart2Day03(filePath: String): Int {

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

                    sum += (cRed * cBlue * cGreen)
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