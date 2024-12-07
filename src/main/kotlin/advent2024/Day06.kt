package advent2024

import utils.BasePuzzle

class Day06 : BasePuzzle() {

    val guards = listOf(">", "<", "^", "v")
    var guardPos = Pair(0, 0)
    var direction = ""
    var initialDirection = ""
    var initialPosition = Pair(0, 0)


    override fun puzzle1(filePath: String): Any {
        val input = getInput(filePath)
        val matrixList = mutableListOf<MutableList<String>>()
        for (line in input) {
            matrixList.add(line.toList().map { it.toString() }.toMutableList())
        }
        val guardPosList = matrixList.flatMapIndexed { rowIndex, row ->
            row.mapIndexedNotNull { colIndex, element ->
                if (element in guards) Pair(colIndex, rowIndex) else null
            }
        }
        assert(guardPosList.size == 1)
        guardPos = guardPosList.first()
        direction = getDirection(matrixList)
        return walkAndMark(matrixList)
    }

    private fun getDirection(matrixList: MutableList<MutableList<String>>): String {
        val s = matrixList[guardPos.second][guardPos.first]
        if (s.equals(">")) return "right"
        else if (s.equals("<")) return "left"
        else if (s.equals("^")) return "up"
        else if (s.equals("v")) return "down"
        else return "end"
    }

    val visitedPos = mutableSetOf<Pair<Int, Int>>()

    private fun walkAndMark(matrixList: MutableList<MutableList<String>>): Any {
        visitedPos.add(guardPos)
        while (guardPos.first in 0 until matrixList.size &&
            guardPos.second in 0 until matrixList[0].size) {
            visitedPos.add(moveIntoDirection(matrixList))
        }
        return visitedPos.toSet().size - 1
    }


    private fun moveIntoDirection(matrixList: MutableList<MutableList<String>>): Pair<Int, Int> {
        if (direction == "left") {
            if (guardPos.first-1 >= 0 ) {
                if (matrixList[guardPos.second][guardPos.first-1] == "#") {
                    direction = "up"
                    return moveIntoDirection(matrixList)
                }
            }
            guardPos = Pair(guardPos.first-1, guardPos.second)

        } else if (direction == "right") {
            if (guardPos.first+1 < matrixList[0].size) {
                if (matrixList[guardPos.second][guardPos.first+1] == "#") {
                    direction = "down"
                    return moveIntoDirection(matrixList)
                }
            }
            guardPos = Pair(guardPos.first+1, guardPos.second)

        } else if (direction == "up") {
            if (guardPos.second-1 >= 0) {
                if (matrixList[guardPos.second - 1][guardPos.first] == "#") {
                    direction = "right"
                    return moveIntoDirection(matrixList)
                }
            }
            guardPos = Pair(guardPos.first, guardPos.second-1)


        } else if (direction == "down") {
            if (guardPos.second+1 < matrixList.size) {
                if (matrixList[guardPos.second+1][guardPos.first] == "#") {
                    direction = "left"
                    return moveIntoDirection(matrixList)
                }
            }
            guardPos = Pair(guardPos.first, guardPos.second+1)
        }
        return guardPos
    }

    override fun puzzle2(filePath: String): Any {
        val input = getInput(filePath)
        val matrixList = mutableListOf<MutableList<String>>()
        for (line in input) {
            matrixList.add(line.toList().map { it.toString() }.toMutableList())
        }
        val guardPosList = matrixList.flatMapIndexed { rowIndex, row ->
            row.mapIndexedNotNull { colIndex, element ->
                if (element in guards) Pair(colIndex, rowIndex) else null
            }
        }
        assert(guardPosList.size == 1)
        guardPos = guardPosList.first()
        initialPosition = guardPosList.first()
        initialDirection = getDirection(matrixList)
        direction = getDirection(matrixList)
        var loops = 0
        for (i in matrixList.indices) {
            for (j in matrixList[i].indices) {
                if (matrixList[i][j] == ".") {
                    matrixList[i][j] = "#"
                    println("replace $i, $j")
                    loops += walkAndSearchForLoop(matrixList)
                    matrixList[i][j] = "."
                    guardPos = initialPosition
                    direction = initialDirection
                }
            }
        }
        return loops

    }

    private fun walkAndSearchForLoop(matrixList: MutableList<MutableList<String>>): Int {
        while (guardPos.first in 0 until matrixList.size &&
            guardPos.second in 0 until matrixList[0].size) {
            moveIntoDirection(matrixList)
            if (direction == initialDirection && guardPos == initialPosition) {
                return 1
            }
        }
        return 0
    }
}