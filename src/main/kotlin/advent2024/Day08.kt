package advent2024

import utils.BasePuzzle
import kotlin.math.abs
import kotlin.math.max

class Day08 : BasePuzzle() {

    override fun puzzle1(filePath: String): Any {
        val input = getInput(filePath)
        val matrixList = convertInputToMatrix(input)
        val antennas = findAntennas(matrixList)
        val maxRows = matrixList.size
        val maxCols = matrixList.size
        val foundLocations = mutableSetOf<Pair<Int, Int>>()
        antennas.toSortedMap()
        for ((antennaNames, antennaPositions) in antennas) {
            for (i in antennaPositions.indices) {
                for (j in i+1 until antennaPositions.size) {
                    val currentLocationsFound = findLocationsWithAntennas(antennaPositions[i], antennaPositions[j], maxRows, maxCols)
                    foundLocations.addAll(currentLocationsFound)
                }
            }
        }
        return foundLocations.size


    }

    override fun puzzle2(filePath: String): Any {
        TODO("Not yet implemented")
    }


    private fun findLocationsWithAntennas(antennaPos1: Pair<Int, Int>, antennaPos2: Pair<Int, Int>, maxRows: Int, maxCols: Int): MutableSet<Pair<Int, Int>> {


        val (p1, p2) = findLocations(antennaPos1, antennaPos2)
        val foundLocations = mutableSetOf<Pair<Int, Int>>()
        if (p1.first in 0..maxRows) {
            if (p1.second in 0..maxCols) {
                foundLocations.add(Pair(p1.first, p1.second))
            }
        }
        if (p2.first in 0..maxRows) {
            if (p2.second in 0..maxCols) {
                foundLocations.add(Pair(p2.first, p2.second))
            }
        }

        return foundLocations
    }

    private fun findLocations(antennaPos1: Pair<Int, Int>, antennaPos2: Pair<Int, Int>):Pair<Pair<Int, Int>, Pair<Int, Int>> {

        val diffX = antennaPos1.first - antennaPos2.first
        val diffY = antennaPos1.second - antennaPos2.second

        val newPos1 = Pair(antennaPos1.first - diffX * 2, antennaPos1.second - diffY * 2)
        val newPos2 = Pair(antennaPos2.first + diffX * 2, antennaPos2.second + diffY * 2)
        println("pos1: (${antennaPos1.first}, ${antennaPos1.second}), pos2: (${antennaPos2.first}, ${antennaPos2.second}), newPos1: (${newPos1.first}, ${newPos1.second}), newPos2: (${newPos2.first}, ${newPos2.second})")
        return Pair(newPos1, newPos2)
    }

    private fun findAntennas(matrixList: MutableList<MutableList<String>>): MutableMap<String, MutableList<Pair<Int, Int>>> {
        val antennas = mutableMapOf<String, MutableList<Pair<Int, Int>>>()
        for (rowIndex in matrixList.indices) {
            for (colIndex in matrixList[rowIndex].indices) {
                if (matrixList[rowIndex][colIndex] != ".") {
                    antennas.getOrPut(matrixList[rowIndex][colIndex]) { mutableListOf() }.add(Pair(rowIndex, colIndex))
                }
            }
        }
        return antennas
    }

    private fun convertInputToMatrix(list: MutableList<String>): MutableList<MutableList<String>> {
        val matrixList = mutableListOf<MutableList<String>>()
        for (line in list) {
            matrixList.add(line.toList().map { it.toString() }.toMutableList())
        }
        return matrixList
    }
}