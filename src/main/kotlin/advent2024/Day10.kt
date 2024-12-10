package advent2024

import utils.BasePuzzle

class Day10 : BasePuzzle() {

    override fun puzzle1(filePath: String): Any {
        val (matrixList, startPoints) = prepareStartPosAndMatrix(filePath)
        var res = 0
        for (startPos in startPoints) {
            val temp = bruteForceWay(startPos, matrixList)
            res += temp
        }
        return res
    }

    override fun puzzle2(filePath: String): Any {
        val (matrixList, startPoints) = prepareStartPosAndMatrix(filePath)
        var res = 0
        for (startPos in startPoints) {
            val temp = bruteForceWithoutVisited(startPos, matrixList)
            res += temp
        }
        return res
    }

    private fun prepareStartPosAndMatrix(filePath: String): Pair<MutableList<MutableList<Int>>, MutableList<Pair<Int, Int>>> {
        val input = getInput(filePath)
        val matrixListOld = convertInputToMatrix(input)
        val matrixList = matrixListOld.map { list -> list.map { it.toInt() }.toMutableList() }.toMutableList()
        val startPoints = findStartPoints(matrixList)
        return Pair(matrixList, startPoints)
    }

    private fun bruteForceWithoutVisited(currentPos: Pair<Int, Int>, matrixList: MutableList<MutableList<Int>>): Int {
        val currentNumber = matrixList[currentPos.first][currentPos.second]
        if (currentNumber == 9) {
            return 1
        }
        val neighbours = getNeighbors(currentPos, matrixList)
        if (neighbours.isEmpty()) return 0
        return neighbours.sumOf { neighbour ->
            bruteForceWithoutVisited(neighbour, matrixList)
        }
    }

    private fun bruteForceWay(currentPos: Pair<Int, Int>, matrixList: MutableList<MutableList<Int>>, visited : MutableSet<Pair<Int, Int>> = mutableSetOf()) : Int {
        if (currentPos in visited) {
            return 0
        }
        visited.add(currentPos)
        val currentNumber = matrixList[currentPos.first][currentPos.second]
        if (currentNumber == 9) {
            return 1
        }
        val neighbours = getNeighbors(currentPos, matrixList)
        if (neighbours.isEmpty()) return 0
        return neighbours.sumOf { neighbour ->
            bruteForceWay(neighbour, matrixList, visited)
        }
    }

    private fun getNeighbors(currentPos: Pair<Int, Int>, matrixList: List<List<Int>>): List<Pair<Int, Int>> {
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
        val filtered =  potentialNeighbors.filter { (x, y) ->
            x in 0 until maxX && y in 0 until maxY
        }
        return filtered.filter { (x,y) ->
            matrixList[x][y] == matrixList[currentX][currentY] + 1
        }

    }

    private fun findStartPoints(matrixList: MutableList<MutableList<Int>>): MutableList<Pair<Int,Int>> {
        var foundPositions = mutableListOf<Pair<Int,Int>>()
        for (i in matrixList.indices) {
            for (j in matrixList[i].indices) {
                if (matrixList[i][j] == 0) {
                    foundPositions.add(Pair(i,j))
                }
            }
        }
        return foundPositions

    }
}