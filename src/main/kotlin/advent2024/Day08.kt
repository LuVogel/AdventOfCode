package advent2024

import utils.BasePuzzle

class Day08 : BasePuzzle() {

    override fun puzzle1(filePath: String): Any {
        val input = getInput(filePath)
        val matrixList = convertInputToMatrix(input)
        val maxRows = matrixList.size-1
        val maxCols = matrixList.size-1
        val antennas = findAntennas(matrixList)
        val foundLocations = mutableSetOf<Pair<Int, Int>>()
        antennas.toSortedMap()
        return getLocationCount(antennas, 1, matrixList, maxRows, maxCols).size


    }

    override fun puzzle2(filePath: String): Any {
        val input = getInput(filePath)
        val matrixList = convertInputToMatrix(input)
        val maxRows = matrixList.size-1
        val maxCols = matrixList.size-1
        val antennas = findAntennas(matrixList)
        antennas.toSortedMap()
        val locations = getLocationCount(antennas,maxRows+maxCols, matrixList, maxRows, maxCols)
        antennas.forEach { locations.addAll(it.value) }
        return locations.size
    }

    private fun getLocationCount(antennas: MutableMap<String, MutableList<Pair<Int, Int>>>, steps: Int, matrixList: MutableList<MutableList<String>>, maxRows: Int, maxCols: Int) : MutableSet<Pair<Int, Int>> {
        val foundLocations = mutableSetOf<Pair<Int, Int>>()
        for ((antennaNames, antennaPositions) in antennas) {
            for (i in antennaPositions.indices) {
                for (j in i+1 until antennaPositions.size) {
                    val currentLocationsFound = findLocationsWithAntennas(antennaPositions[i], antennaPositions[j], maxRows, maxCols, steps)
                    foundLocations.addAll(currentLocationsFound)
                }
            }
        }
        return foundLocations
    }


    private fun findLocationsWithAntennas(antennaPos1: Pair<Int, Int>, antennaPos2: Pair<Int, Int>, maxRows: Int, maxCols: Int, steps: Int): MutableSet<Pair<Int, Int>> {
        val foundLocations = mutableSetOf<Pair<Int, Int>>()
        val tempLocations = mutableSetOf<Pair<Int, Int>>()
        for (m in 2 until (2 + steps)) {
            val (p1, p2) = findLocations(antennaPos1, antennaPos2, m)
            tempLocations.add(p1)
            tempLocations.add(p2)
        }
        for (p in tempLocations) {
            if (p.first in 0..maxRows) {
                if (p.second in 0..maxCols) {
                    foundLocations.add(Pair(p.first, p.second))
                }
            }
        }
        return foundLocations
    }

    private fun findLocations(antennaPos1: Pair<Int, Int>, antennaPos2: Pair<Int, Int>, multiplyBy : Int):Pair<Pair<Int, Int>, Pair<Int, Int>> {

            val diffX = antennaPos1.first - antennaPos2.first
            val diffY = antennaPos1.second - antennaPos2.second

            val newPos1 = Pair(antennaPos1.first - (diffX * multiplyBy), antennaPos1.second - (diffY * multiplyBy))
            val newPos2 = Pair(antennaPos2.first + (diffX * multiplyBy), antennaPos2.second + (diffY * multiplyBy))
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