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
                var sum = 0
                val matrix : MutableList<MutableList<String>> = mutableListOf()
                while (bufferedReader.readLine().also { line = it } != null) {
                    val tmp = line!!.split("").toMutableList()
                    tmp.removeAt(0)
                    tmp.removeAt(tmp.lastIndex)
                    matrix.add(tmp)
                }
                val res = checkMatrixPart1(matrix)
                return sum
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    private fun checkMatrixPart1(matrix: MutableList<MutableList<String>>): Any {
        val sum = 0
        for (coords in matrix.indices) {
            for (index in matrix[coords].indices) {
                val sym = matrix[coords][index]
                if (sym != "." && sym.toIntOrNull() == null) {
                    // symbol found
                    sum += getPartNumbers(matrix, coords, index)
                }
            }
        }
        return 0

    }

    private fun getPartNumbers(matrix: MutableList<MutableList<String>>, yAxis: Int, xAxis: Int): Any {
        if (yAxis == 0 && xAxis == 0) {
            //check top left corner
            if (matrix[yAxis][xAxis+1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis+1][xAxis+1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis+1][xAxis].toIntOrNull() != null) {
                //found
            }
        } else if (yAxis == matrix.lastIndex && xAxis == 0) {
            //check down left corner
            if (matrix[yAxis][xAxis+1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis-1][xAxis+1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis-1][xAxis].toIntOrNull() != null) {
                //found
            }
        } else if (yAxis == matrix.lastIndex && xAxis == matrix[0].lastIndex) {
            //check down right corner
            if (matrix[yAxis][xAxis-1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis-1][xAxis-1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis-1][xAxis].toIntOrNull() != null) {
                //found
            }
        } else if (yAxis == 0 && xAxis == matrix[0].lastIndex) {
            //check top right corner
            if (matrix[yAxis][xAxis-1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis+1][xAxis-1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis+1][xAxis].toIntOrNull() != null) {
                //found
            }
        } else if (yAxis == 0) {
            //check top side without corner
            if (matrix[yAxis][xAxis+1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis+1][xAxis+1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis+1][xAxis].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis+1][xAxis+-].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis][xAxis-1].toIntOrNull() != null) {
                //found
            }
        } else if (yAxis == matrix.lastIndex) {
            //check bottom side without corner
            if (matrix[yAxis][xAxis+1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis-1][xAxis+1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis-1][xAxis].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis-1][xAxis-1].toIntOrNull() != null) {
                //found
            }
            if (matrix[yAxis][xAxis-1].toIntOrNull() != null) {
                //found
            }
        } else if (xAxis == 0) {
            //check left side without corner
        } else if (xAxis == matrix[0].lastIndex) {
            //check right side without corner
        }
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