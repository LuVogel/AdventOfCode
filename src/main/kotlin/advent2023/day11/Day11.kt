package advent2023.day11

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import kotlin.math.abs


class Day11 {




    fun solveDay11(filePath: String, expansion: Long): Long {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                var grid : MutableList<MutableList<String>> = mutableListOf()
                var startGalaxyPos : MutableList<Pair<Int, Int>> = mutableListOf()
                var yCounter = 0
                while (bufferedReader.readLine().also { lines = it } != null) {
                    var xCounter = 0
                    var tempList : MutableList<String> = mutableListOf()
                    for (i in lines!!.split("")) {
                        if (i.isNotBlank()) {
                            if (i == "#") {
                                startGalaxyPos.add(Pair(xCounter, yCounter))
                            }
                            tempList.add(i)
                            xCounter += 1
                        }
                    }
                    grid.add(tempList)
                    yCounter += 1
                }
                var emptyPositions = getEmptyPositions(startGalaxyPos, grid.size, grid[0].size)
                var emptyRows = emptyPositions[1]
                grid = expandGrid(grid, emptyRows, expansion)
//                for (line in grid) {
//                    for (symbol in line) {
//                        print(symbol)
//                    }
//                    println()
//                }
                var pairs = getPairsFromNewStartPos(grid)
                var distancesBetweenPairs : Long = 0L
                for (pair in pairs) {
                    var x1 = pair.first.first
                    var x2 = pair.second.first
                    var y1 = pair.first.second
                    var y2 = pair.second.second
                    var manhattanDistance = abs(x2.toLong() - x1.toLong()) + abs(y2.toLong() - y1.toLong())

                    distancesBetweenPairs += manhattanDistance
                }

                return distancesBetweenPairs
            }
        } catch (e: IOException) {
            e.printStackTrace().also { print("error in try catch") }
        }
        return 0
    }

    private fun getPairsFromNewStartPos(grid: MutableList<MutableList<String>>): MutableList<Triple<Pair<Int, Int>, Pair<Int, Int>, String>> {
        var newPos : MutableList<Pair<Int, Int>> = mutableListOf()
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                var symbol = grid[y][x]
                if (symbol.toIntOrNull() != null) {
                    newPos.add(Pair(x, y))
                }
            }
        }
        var pairs : MutableList<Triple<Pair<Int, Int>, Pair<Int, Int>, String>> = mutableListOf()
        var counterFirst = 1
        for (i in newPos.indices) {
            var counterSecond = 1
            for (j in i + 1 until newPos.size) {
                pairs.add(Triple(newPos[i], newPos[j], "$counterFirst and $counterSecond"))
            }
        }
        return pairs
    }


    private fun successors(grid: MutableList<MutableList<String>>, currentNode: Pair<Int, Int>): MutableList<Pair<Int, Int>> {
        var maxX = grid[0].size
        var maxY = grid.size
        var currentX = currentNode.first
        var currentY = currentNode.second
        var successors : MutableList<Pair<Int, Int>> = mutableListOf()
        if (currentX == 0 && currentY == 0) {
            //top left corner
            successors.add(Pair(currentX+1, currentY))
            successors.add(Pair(currentX, currentY+1))
        } else if (currentX == 0 && currentY == maxY) {
            //down left corner
            successors.add(Pair(currentX+1, currentY))
            successors.add(Pair(currentX, currentY-1))
        } else if (currentX == maxX && currentY == 0) {
            //top right corner
            successors.add(Pair(currentX, currentY+1))
            successors.add(Pair(currentX-1, currentY))
        } else if (currentX == maxX && currentY == maxY) {
            //down right corner
            successors.add(Pair(currentX, currentY-1))
            successors.add(Pair(currentX-1, currentY))
        } else if (currentX == 0) {
            //left border without corners
            successors.add(Pair(currentX, currentY-1))
            successors.add(Pair(currentX, currentY+1))
            successors.add(Pair(currentX+1, currentY))
        } else if (currentY == 0) {
            //top border without corners
            successors.add(Pair(currentX+1, currentY))
            successors.add(Pair(currentX-1, currentY))
            successors.add(Pair(currentX, currentY+1))
        } else if (currentX == maxX) {
            //right border without corners
            successors.add(Pair(currentX, currentY+1))
            successors.add(Pair(currentX, currentY-1))
            successors.add(Pair(currentX-1, currentY))
        } else if (currentY == maxY) {
            //down border without corners
            successors.add(Pair(currentX+1, currentY))
            successors.add(Pair(currentX-1, currentY))
            successors.add(Pair(currentX, currentY-1))
        } else {
            //not on border
            successors.add(Pair(currentX+1, currentY))
            successors.add(Pair(currentX-1, currentY))
            successors.add(Pair(currentX, currentY+1))
            successors.add(Pair(currentX, currentY-1))
        }
        return successors
    }






    private fun expandGrid(grid: MutableList<MutableList<String>>, emptyRows: MutableList<Int>, expansion: Long): MutableList<MutableList<String>> {
        var expandedGrid : MutableList<MutableList<String>> = mutableListOf()
        var counter = 1
        for (lines in grid) {
            if (lines.all { it == "." }) {
                for (i in emptyRows) {
                    for (j in 0 until expansion) {
                        lines.add(".")
                    }
                }
                for (i in 0 until expansion+1) {
                    expandedGrid.add(lines)
                }

            } else {
                var temporaryLine : MutableList<String> = mutableListOf()
                for (i in lines.indices) {
                    var s = ""
                    if (lines[i] == "#") {
                        s = counter.toString()
                        counter += 1
                    } else {
                        s = lines[i]
                    }
                    if (i in emptyRows) {
                        for (j in 0 until expansion) {
                            temporaryLine.add(s)
                        }
                    }
                    temporaryLine.add(s)
                }
                expandedGrid.add(temporaryLine)
            }
        }
        return expandedGrid
    }

    private fun getEmptyPositions(grid: MutableList<Pair<Int, Int>>, lineSize: Int, rowSize: Int): MutableList<MutableList<Int>> {
        var linePos : MutableList<Int> = (0 until lineSize).toMutableList()
        var rowPos : MutableList<Int> = (0 until rowSize).toMutableList()
        for (pos in grid) {
            var xPos = pos.first
            var yPos = pos.second
            if (linePos.contains(yPos)) {
                linePos.remove(yPos)
            }
            if (rowPos.contains(xPos)) {
                rowPos.remove(xPos)
            }
        }
        return mutableListOf(linePos, rowPos)

    }


    fun solvePart2Day11(filePath: String): Long {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        val width = file.readLines().size
        val height = file.useLines { l ->
            l.first().length
        }
        val twoDArray : Array<Array<String>> = Array(width) { Array(height) { "" } }
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                var yCount = 0
                var startXPosition = 0
                var startYPosition = 0
                while (bufferedReader.readLine().also { lines = it } != null) {
                    var xCount = 0
                    var split = lines!!.split("")
                    for (symbol in split) {
                        if (symbol.isNotBlank()) {
                            if (symbol == "S") {
                                startXPosition = xCount
                                startYPosition = yCount
                            }
                            twoDArray[yCount][xCount] = symbol
                            xCount += 1
                        }
                    }
                    yCount += 1
                }
                for (y in twoDArray.indices) {
                    for (x in twoDArray[y].indices) {
                        var currentSymbol = twoDArray[y][x]
                        if (currentSymbol == ".") {

                        }
                    }
                }
                return 0
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0

    }
}


