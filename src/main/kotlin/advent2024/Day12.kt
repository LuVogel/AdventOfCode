package advent2024

import utils.BasePuzzle

class Day12 : BasePuzzle() {


    data class Area( var name : String = "",
                     var positions : MutableList<Pair<Int,Int>> = mutableListOf(),
                     var perimeter : Int = 0,
                     var sides: Int = 0)

    private var visited = mutableListOf<Pair<Int, Int>>()
    private var maxI = 0
    private var maxJ = 0
    private var matrixList: MutableList<MutableList<String>> = mutableListOf()
    private var areas = mutableListOf<Area>()
    private var currentPath = mutableListOf<Pair<Int,Int>>()


    override fun puzzle1(filePath: String): Any {
        val input = getInput(filePath)
        matrixList = convertInputToMatrix(input)
        maxI = matrixList.size
        maxJ = matrixList[0].size
        areas = getAreas()
        getPerimeters()
        return areas.sumOf { area ->
            area.perimeter * area.positions.size
        }

    }

    override fun puzzle2(filePath: String): Any {
        val input = getInput(filePath)
        matrixList = convertInputToMatrix(input)
        maxI = matrixList.size
        maxJ = matrixList[0].size
        areas = getAreas()
        for (area in areas) {
            area.sides = countCorners(area)
        }
        return areas.sumOf { area ->
            area.sides * area.positions.size
        }
    }

    private fun countCorners(area: Area): Int {
        val directions = listOf(
            Pair(-1, 0), // Top
            Pair(-1, 1), // Top Right
            Pair(0, 1),  // Right
            Pair(1, 1),  // Bottom Right
            Pair(1, 0),  // Bottom
            Pair(1, -1), // Bottom Left
            Pair(0, -1), // Left
            Pair(-1, -1) // Top Left
        )
        var corners = 0
        for (pos in area.positions) {
            val (row, col) = pos
            val adjacency = directions.map { (di, dj) ->
                val ni = row + di
                val nj = col + dj
                ni to nj in area.positions
            }
            if ((!adjacency[0] && !adjacency[2]) || (adjacency[0] && !adjacency[1] && adjacency[2])) corners++ // Top-Right Corner
            if ((!adjacency[2] && !adjacency[4]) || (adjacency[2] && !adjacency[3] && adjacency[4])) corners++ // Bottom-Right Corner
            if ((!adjacency[4] && !adjacency[6]) || (adjacency[4] && !adjacency[5] && adjacency[6])) corners++ // Bottom-Left Corner
            if ((!adjacency[6] && !adjacency[0]) || (adjacency[6] && !adjacency[7] && adjacency[0])) corners++ // Top-Left Corner

        }
        return corners
    }


    private fun getPerimeters() {
        for (area in areas) {
            var sum = 0
            for (pos in area.positions) {
                val neighbours = getNeighbors(pos, matrixList).filter { (x,y) ->
                    matrixList[x][y] == area.name
                }
                sum += (4 - neighbours.size)
            }
            area.perimeter = sum
        }
    }

    private fun getAreas(): MutableList<Area> {
        val areas = mutableListOf<Area>()
        for (i in matrixList.indices) {
            for (j in matrixList[i].indices) {
                if (!visited.contains(Pair(i, j))) {
                    val found = findAllNodesInPath(matrixList, i, j)
                    val area = Area()
                    area.name = matrixList[i][j]
                    area.positions.addAll(found)
                    currentPath.clear()
                    if (area.positions.isNotEmpty()) {
                        areas.add(area)
                    }
                }

            }
        }
        return areas
    }

    private fun findAllNodesInPath(
        matrixList: MutableList<MutableList<String>>,
        startI: Int,
        startJ: Int,
    ): MutableList<Pair<Int, Int>> {
        if (visited.contains(Pair(startI, startJ))) {
            return mutableListOf()
        } else {
            visited.add(Pair(startI, startJ))
            currentPath.add(startI to startJ)
            val neighbours = getNeighbors(Pair(startI, startJ), matrixList).filter { (x,y) ->
                matrixList[x][y] == matrixList[startI][startJ]
            }
            for (neighbour in neighbours) {
                findAllNodesInPath(matrixList, neighbour.first, neighbour.second)
            }
            return currentPath
        }

    }

    private fun getNeighbors(currentPos: Pair<Int, Int>, matrixList: MutableList<MutableList<String>>): List<Pair<Int, Int>> {
        val currentX = currentPos.first
        val currentY = currentPos.second
        val maxX = matrixList.size
        val maxY = matrixList[0].size

        val potentialNeighbors = listOf(
            Pair(currentX - 1, currentY),
            Pair(currentX + 1, currentY),
            Pair(currentX, currentY - 1),
            Pair(currentX, currentY + 1)
        )
        return potentialNeighbors.filter { (x, y) ->
            x in 0 until maxX && y in 0 until maxY
        }
    }


}