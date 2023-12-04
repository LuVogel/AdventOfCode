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
                    if (tmp[0] == "") {
                        tmp.removeAt(0)
                    }
                    if (tmp[tmp.lastIndex] == "") {
                        tmp.removeAt(tmp.lastIndex)
                    }
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
        for (yIndex in 0 until matrix.lastIndex+1) {
            var foundNumber  = ""
            var foundXCoords : MutableList<Int> = mutableListOf()
            var foundYCoords : MutableList<Int> = mutableListOf()
            for (xIndex in 0 until matrix[yIndex].lastIndex+1) {
                val currentSymbol = matrix[yIndex][xIndex]
                if (currentSymbol.toIntOrNull() != null) {
                    foundNumber += currentSymbol
                    foundXCoords.add(xIndex)
                    foundYCoords.add(yIndex)
                    if (xIndex == matrix[yIndex].lastIndex) {
                        if (checkNumber(foundXCoords, foundYCoords, matrix)) sum += foundNumber.toInt()
                        foundNumber = ""
                        foundXCoords.clear()
                        foundYCoords.clear()
                    }
                } else if (foundNumber != "") {
                    if (checkNumber(foundXCoords, foundYCoords, matrix)) sum += foundNumber.toInt()
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
            if (yAxis == 0 && xAxis == 0) {
                //check top left corner
                if (matrix[yAxis][xAxis+1].toIntOrNull() == null && matrix[yAxis][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis+1].toIntOrNull() == null && matrix[yAxis+1][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis+1][xAxis] != ".") {
                    //found
                    return true
                }
            } else if (yAxis == matrix.lastIndex && xAxis == 0) {
                //check down left corner
                if (matrix[yAxis][xAxis+1].toIntOrNull() == null && matrix[yAxis][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis-1][xAxis+1].toIntOrNull() == null && matrix[yAxis-1][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis-1][xAxis] != ".") {
                    //found
                    return true
                }
            } else if (yAxis == matrix.lastIndex && xAxis == matrix[0].lastIndex) {
                //check down right corner
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis-1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis-1][xAxis-1].toIntOrNull() == null && matrix[yAxis-1][xAxis-1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis-1][xAxis] != ".") {
                    //found
                    return true
                }
            } else if (yAxis == 0 && xAxis == matrix[0].lastIndex) {
                //check top right corner
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis-1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis-1].toIntOrNull() == null && matrix[yAxis+1][xAxis-1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis+1][xAxis] != ".") {
                    //found
                    return true
                }
            } else if (yAxis == 0) {
                //check top side without corner
                if (matrix[yAxis][xAxis+1].toIntOrNull() == null && matrix[yAxis][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis+1].toIntOrNull() == null && matrix[yAxis+1][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis+1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis-1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis-1].toIntOrNull() == null && matrix[yAxis+1][xAxis-1] != ".") {
                    //found
                    return true
                }
            } else if (yAxis == matrix.lastIndex) {
                //check bottom side without corner
                if (matrix[yAxis][xAxis+1].toIntOrNull() == null && matrix[yAxis][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis-1][xAxis+1].toIntOrNull() == null && matrix[yAxis-1][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis-1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis-1][xAxis-1].toIntOrNull() == null && matrix[yAxis-1][xAxis-1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis-1] != ".") {
                    //found
                    return true
                }
            } else if (xAxis == 0) {
                //check left side without corner
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis-1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis+1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis-1][xAxis+1].toIntOrNull() == null && matrix[yAxis-1][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis+1].toIntOrNull() == null && matrix[yAxis+1][xAxis+1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis][xAxis+1].toIntOrNull() == null && matrix[yAxis][xAxis+1] != ".") {
                    //found
                    return true
                }
            } else if (xAxis == matrix[0].lastIndex) {
                //check right side without corner
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis-1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis+1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis-1][xAxis-1].toIntOrNull() == null && matrix[yAxis-1][xAxis-1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis-1].toIntOrNull() == null && matrix[yAxis+1][xAxis-1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis-1] != ".") {
                    //found
                    return true
                }
            } else {
                //not on boarder
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis-1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis+1][xAxis] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis-1] != ".") {
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
                if (matrix[yAxis+1][xAxis-1].toIntOrNull() == null && matrix[yAxis + 1][xAxis-1] != ".") {
                    //found
                    return true
                }
                if (matrix[yAxis-1][xAxis-1].toIntOrNull() == null && matrix[yAxis-1][xAxis-1] != ".") {
                    //found
                    return true
                }
            }
        }
        return false

    }

    fun solvePart2Day03(filePath: String): Long {
        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())

        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var line: String?
                val matrix : MutableList<MutableList<String>> = mutableListOf()
                while (bufferedReader.readLine().also { line = it } != null) {
                    val tmp = line!!.split("").toMutableList()
                    if (tmp[0] == "") {
                        tmp.removeAt(0)
                    }
                    if (tmp[tmp.lastIndex] == "") {
                        tmp.removeAt(tmp.lastIndex)
                    }
                    matrix.add(tmp)
                }
                return checkMatrixPart2(matrix)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    private fun checkMatrixPart2(matrix: MutableList<MutableList<String>>): Long {
        var gearMap : MutableMap<Pair<Int, Int>, MutableList<Int>> = mutableMapOf()
        for (yIndex in 0 until matrix.lastIndex+1) {
            var foundNumber  = ""
            var foundXCoords : MutableList<Int> = mutableListOf()
            var foundYCoords : MutableList<Int> = mutableListOf()
            for (xIndex in 0 until matrix[yIndex].lastIndex+1) {
                val currentSymbol = matrix[yIndex][xIndex]
                if (currentSymbol.toIntOrNull() != null) {
                    foundNumber += currentSymbol
                    foundXCoords.add(xIndex)
                    foundYCoords.add(yIndex)
                    if (xIndex == matrix[yIndex].lastIndex) {
                        var list = checkNumberPart2(foundXCoords, foundYCoords, matrix)
                        for (coordPair in list) {
                            val pair : Pair<Int, Int> = Pair(coordPair.first, coordPair.second)
                            gearMap.computeIfAbsent(pair) { mutableListOf() }.add(foundNumber.toInt())
                        }

                        foundNumber = ""
                        foundXCoords.clear()
                        foundYCoords.clear()
                    }
                } else if (foundNumber != "") {
                    var list = checkNumberPart2(foundXCoords, foundYCoords, matrix)
                    for (coordPair in list) {
                        val pair : Pair<Int, Int> = Pair(coordPair.first, coordPair.second)
                        gearMap.computeIfAbsent(pair) { mutableListOf() }.add(foundNumber.toInt())
                    }

                    foundNumber = ""
                    foundXCoords.clear()
                    foundYCoords.clear()
                }
            }
        }
        var sum : Long = 0
        for (gear in gearMap.values) {
            var prod : Long = 1
            if (gear.size > 1) {
                for (num in gear) {
                    prod *= num.toLong()
                }
            } else {
                prod = 0
            }
            sum += prod
        }
        return sum
    }

    private fun checkNumberPart2(foundXCoords: MutableList<Int>, foundYCoords: MutableList<Int>, matrix: MutableList<MutableList<String>>): List<Pair<Int, Int>> {
        var list : MutableList<Pair<Int, Int>> = mutableListOf()
        for (i in 0 until foundYCoords.lastIndex+1) {
            val xAxis = foundXCoords[i]
            val yAxis = foundYCoords[i]
            if (yAxis == 0 && xAxis == 0) {
                //check top left corner
                if (matrix[yAxis][xAxis+1].toIntOrNull() == null && matrix[yAxis][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis+1].toIntOrNull() == null && matrix[yAxis+1][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis+1)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis+1][xAxis] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis+1)
                    list.add(pair)
                }
            } else if (yAxis == matrix.lastIndex && xAxis == 0) {
                //check down left corner
                if (matrix[yAxis][xAxis+1].toIntOrNull() == null && matrix[yAxis][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis)
                    list.add(pair)
                }
                if (matrix[yAxis-1][xAxis+1].toIntOrNull() == null && matrix[yAxis-1][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis-1)
                    list.add(pair)
                }
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis-1][xAxis] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis-1)
                    list.add(pair)
                }
            } else if (yAxis == matrix.lastIndex && xAxis == matrix[0].lastIndex) {
                //check down right corner
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis)
                    list.add(pair)
                }
                if (matrix[yAxis-1][xAxis-1].toIntOrNull() == null && matrix[yAxis-1][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis-1)
                    list.add(pair)
                }
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis-1][xAxis] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis-1)
                    list.add(pair)
                }
            } else if (yAxis == 0 && xAxis == matrix[0].lastIndex) {
                //check top right corner
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis-1].toIntOrNull() == null && matrix[yAxis+1][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis+1)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis+1][xAxis] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis+1)
                    list.add(pair)
                }
            } else if (yAxis == 0) {
                //check top side without corner
                if (matrix[yAxis][xAxis+1].toIntOrNull() == null && matrix[yAxis][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis+1].toIntOrNull() == null && matrix[yAxis+1][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis+1)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis+1][xAxis] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis+1)
                    list.add(pair)
                }
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis-1)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis-1].toIntOrNull() == null && matrix[yAxis+1][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis+1)
                    list.add(pair)
                }
            } else if (yAxis == matrix.lastIndex) {
                //check bottom side without corner
                if (matrix[yAxis][xAxis+1].toIntOrNull() == null && matrix[yAxis][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis)
                    list.add(pair)
                }
                if (matrix[yAxis-1][xAxis+1].toIntOrNull() == null && matrix[yAxis-1][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis-1)
                    list.add(pair)
                }
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis-1][xAxis] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis-1)
                    list.add(pair)
                }
                if (matrix[yAxis-1][xAxis-1].toIntOrNull() == null && matrix[yAxis-1][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis-1)
                    list.add(pair)
                }
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis)
                    list.add(pair)
                }
            } else if (xAxis == 0) {
                //check left side without corner
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis-1][xAxis] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis-1)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis+1][xAxis] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis+1)
                    list.add(pair)
                }
                if (matrix[yAxis-1][xAxis+1].toIntOrNull() == null && matrix[yAxis-1][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis-1)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis+1].toIntOrNull() == null && matrix[yAxis+1][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis+1)
                    list.add(pair)
                }
                if (matrix[yAxis][xAxis+1].toIntOrNull() == null && matrix[yAxis][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis)
                    list.add(pair)
                }
            } else if (xAxis == matrix[0].lastIndex) {
                //check right side without corner
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis-1][xAxis] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis-1)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis+1][xAxis] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis+1)
                    list.add(pair)
                }
                if (matrix[yAxis-1][xAxis-1].toIntOrNull() == null && matrix[yAxis-1][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis-1)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis-1].toIntOrNull() == null && matrix[yAxis+1][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis+1)
                    list.add(pair)
                }
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis)
                    list.add(pair)
                }
            } else {
                //not on boarder
                if (matrix[yAxis-1][xAxis].toIntOrNull() == null && matrix[yAxis-1][xAxis] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis-1)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis].toIntOrNull() == null && matrix[yAxis+1][xAxis] != ".") {
                    //found
                    val pair = Pair(xAxis, yAxis+1)
                    list.add(pair)
                }
                if (matrix[yAxis][xAxis-1].toIntOrNull() == null && matrix[yAxis][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis)
                    list.add(pair)
                }
                if (matrix[yAxis][xAxis+1].toIntOrNull() == null && matrix[yAxis][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis+1].toIntOrNull() == null && matrix[yAxis+1][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis+1)
                    list.add(pair)
                }
                if (matrix[yAxis-1][xAxis+1].toIntOrNull() == null && matrix[yAxis-1][xAxis+1] != ".") {
                    //found
                    val pair = Pair(xAxis+1, yAxis-1)
                    list.add(pair)
                }
                if (matrix[yAxis+1][xAxis-1].toIntOrNull() == null && matrix[yAxis + 1][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis+1)
                    list.add(pair)
                }
                if (matrix[yAxis-1][xAxis-1].toIntOrNull() == null && matrix[yAxis-1][xAxis-1] != ".") {
                    //found
                    val pair = Pair(xAxis-1, yAxis-1)
                    list.add(pair)
                }
            }
        }
        return list.toSet().toMutableList()

    }

}