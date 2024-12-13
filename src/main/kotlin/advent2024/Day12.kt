package advent2024

import utils.BasePuzzle

class Day12 : BasePuzzle() {


    data class Area( var name : String = "",
                     var positions : MutableList<Pair<Int,Int>> = mutableListOf(),
                     var perimeter : Int = 0,
                     var sides: Int = 0)



    private val directions = listOf(
        Pair(1, 0),
        Pair(-1, 0),
        Pair(0, -1),
        Pair(0, 1)
    )
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
            area.sides = countSides(area)
        }
        for (area in areas) {
            println("area: ${area.name} with pos: ${area.positions}\n" +
                    "and sides: ${area.sides}")
        }
        return areas.sumOf { area ->
            area.sides * area.positions.size
        }
    }

    private fun countSides(area: Area): Int {
        var sides = 0
      for (position in area.positions) {
          val currentBlock = matrixList[position.first][position.second]
          val list = findEightNeighbours(currentBlock, position.first, position.second)
          if ((!list[0] && list[2]) || (list[0] && !list[1] && list[2])) sides++
          if ((!list[2] && !list[4] || (list[2] && !list[3] && list[4]))) sides++
          if ((!list[4] && !list[6]) || (list[4] && !list[5] && list[6])) sides++
          if ((!list[6] && !list[0]) || (list[6] && !list[7] && list[0])) sides++
      }
        return sides

    }

    private fun findEightNeighbours(currentBlock : String, row : Int, col : Int):  List<Boolean>{
        val neigh1 = row -1 >= 0 && matrixList[row-1][col] == currentBlock //top
        val neigh2 = row - 1 >= 0 && col + 1 < matrixList[0].size && matrixList[row - 1][col + 1] == currentBlock // top right
        val neigh3 = col + 1 < matrixList[0].size && matrixList[row][col + 1] == currentBlock // right
        val neigh4 = row + 1 < matrixList.size && col + 1 < matrixList[0].size && matrixList[row + 1][col + 1] == currentBlock // bottom right
        val neigh5 = row + 1 < matrixList.size && matrixList[row + 1][col] == currentBlock // bottom
        val neigh6 = row + 1 < matrixList.size && col - 1 >= 0 && matrixList[row + 1][col - 1] == currentBlock // bottom left
        val neigh7 = col - 1 >= 0 && matrixList[row][col - 1] == currentBlock // left
        val neigh8 = row - 1 >= 0 && col - 1 >= 0 && matrixList[row - 1][col - 1] == currentBlock // top left
        return listOf(neigh1, neigh2, neigh3, neigh4, neigh5, neigh6, neigh7, neigh8)
    }




//
//    fun isEdge(x: Int, y: Int): Boolean {
//
//        for ((dx, dy) in directions) {
//            val nx = x + dx
//            val ny = y + dy
//            if (nx !in matrixList.indices || ny !in matrixList[0].indices || matrixList[nx][ny] != matrixList[x][y]) {
//                return true
//            }
//        }
//        return false
//    }
    fun countEdges(area: Area): Int {
        if (area.positions.isEmpty()) return 0

        val directions = listOf(
            Pair(-1, 0), // oben
            Pair(1, 0),  // unten
            Pair(0, -1), // links
            Pair(0, 1)   // rechts
        )

        val groupSet = area.positions.toSet() // Schnelle Überprüfung, ob ein Punkt zur Gruppe gehört
        var edgeCount = 0

        for ((x, y) in area.positions) {
            for ((dx, dy) in directions) {
                val nx = x + dx
                val ny = y + dy

                // Prüfe, ob der Nachbar außerhalb des Rasters liegt oder nicht zur Gruppe gehört
                if (nx !in matrixList.indices || ny !in matrixList[0].indices || Pair(nx, ny) !in groupSet) {
                    edgeCount++
                }
            }
        }

        return edgeCount
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