package advent2023.day10

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


class Day10 {


    private fun nextSymbolIsPossible(twoDArray: Array<Array<String>>, xPosition: Int, yPosition: Int, direction: String): Boolean {
        var next = ""
        if (direction == "up") {
            next = twoDArray[yPosition][xPosition-1]
            if (next == "L" || next == "-") {
                return false
            }
        } else if (direction == "down") {
            next = twoDArray[yPosition][xPosition+1]
            if (next == "L" || next == "-") {
                return false
            }
        } else if (direction == "left") {
            next = twoDArray[yPosition-1][xPosition]
            if (next == "|" || next == "J" || next == "7") {
                return false
            }
        } else if (direction == "right") {
            next = twoDArray[yPosition+1][xPosition]
            if (next == "|" || next == "L" || next == "F") {
                return false
            }
        }
        return true
    }

    fun solvePart1Day10(filePath: String): Long {

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
                var currentXPosition = 0
                var currentYPosition = 0
                while (bufferedReader.readLine().also { lines = it } != null) {
                    var xCount = 0
                    var split = lines!!.split("")
                    for (symbol in split) {
                        if (symbol.isNotBlank()) {
                            if (symbol == "S") {
                                currentXPosition = xCount
                                currentYPosition = yCount
                            }
                            twoDArray[yCount][xCount] = symbol
                            xCount += 1
                        }
                    }
                    yCount += 1
                }
                val startXPosition = currentXPosition
                val startYPosition = currentYPosition
                val symbols : List<String> = listOf("|", "-", "J", "L", "F", "7")
                for (currentSymbol in symbols) {
                    var maxDistance = 0L
                    var lastXPosition = Int.MIN_VALUE
                    var lastYPosition = Int.MIN_VALUE
                    currentXPosition = startXPosition
                    currentYPosition = startYPosition
                    while (!goalFound(startXPosition, startYPosition, currentXPosition, currentYPosition, maxDistance)) {
                        if (maxDistance == 0L) {
                            var pos = findNextPossibleSymbol(twoDArray, currentXPosition, currentYPosition, currentSymbol)
                                ?: break
                            currentXPosition = pos.first.first
                            currentYPosition = pos.first.second
                            lastXPosition = pos.second.first
                            lastYPosition = pos.second.second
                            maxDistance += 1
                        } else {
                            var pos = findNextSymbol(twoDArray, currentXPosition, currentYPosition, lastXPosition, lastYPosition)
                                ?: break
                            currentXPosition = pos.first.first
                            currentYPosition = pos.first.second
                            lastXPosition = pos.second.first
                            lastYPosition = pos.second.second
                            maxDistance += 1
                        }
                    }
                    if (goalFound(startXPosition, startYPosition, currentXPosition, currentYPosition, maxDistance)) {
                        return (maxDistance / 2).toLong()
                    }
                }

                return 0
            }
        } catch (e: IOException) {
            e.printStackTrace().also { print("error in try catch") }
        }
        return 0
    }

    private fun findNextSymbol(twoDArray: Array<Array<String>>, currentXPosition: Int, currentYPosition: Int, lastXPosition: Int, lastYPosition: Int): Pair<Pair<Int, Int>, Pair<Int, Int>>? {
        var currentSymbol = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition)
        if (currentSymbol == "|") {
            //north-south
            if (lastYPosition == currentYPosition-1) {
                //going down
                var nextDown = twoDArray.getOrNull(currentYPosition+1)?.getOrNull(currentXPosition)
                if (nextDown == "J" || nextDown == "L" || nextDown == "|" || nextDown == "S") {
                    return Pair(Pair(currentXPosition, currentYPosition+1), Pair(currentXPosition, currentYPosition))
                }
            } else if (lastYPosition == currentYPosition+1) {
                //going up
                var nextUp = twoDArray.getOrNull(currentYPosition-1)?.getOrNull(currentXPosition)
                if (nextUp == "|" || nextUp == "7" || nextUp == "F" || nextUp == "S") {
                    return Pair(Pair(currentXPosition, currentYPosition-1), Pair(currentXPosition, currentYPosition))
                }
            }
        } else if (currentSymbol == "-") {
            //west-east
            if (lastXPosition == currentXPosition+1) {
                //goingLeft
                var nextLeft = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition-1)
                if (nextLeft == "-" || nextLeft == "L" || nextLeft == "F" || nextLeft == "S") {
                    return Pair(Pair(currentXPosition-1, currentYPosition), Pair(currentXPosition, currentYPosition))
                }
            } else if (lastXPosition == currentXPosition-1) {
                //goingRight
                var nextRight = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition+1)
                if (nextRight == "J" || nextRight == "-" || nextRight == "7" || nextRight == "S") {
                    return Pair(Pair(currentXPosition+1, currentYPosition), Pair(currentXPosition, currentYPosition))
                }
            }

        } else if (currentSymbol == "J") {
            //north-west
            if (lastXPosition == currentXPosition-1) {
                //goingUp
                var nextUp = twoDArray.getOrNull(currentYPosition-1)?.getOrNull(currentXPosition)
                if (nextUp == "|" || nextUp == "7" || nextUp == "F" || nextUp == "S") {
                    return Pair(Pair(currentXPosition, currentYPosition-1), Pair(currentXPosition, currentYPosition))
                }
            } else if (lastYPosition == currentYPosition-1) {
                //goingLeft
                var nextLeft = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition-1)
                if (nextLeft == "-" || nextLeft == "L" || nextLeft == "F" || nextLeft == "S") {
                    return Pair(Pair(currentXPosition-1, currentYPosition), Pair(currentXPosition, currentYPosition))
                }
            }

        } else if (currentSymbol == "L") {
            //north-east
            if (lastXPosition == currentXPosition+1) {
                //goingUp
                var nextUp = twoDArray.getOrNull(currentYPosition-1)?.getOrNull(currentXPosition)
                if (nextUp == "|" || nextUp == "7" || nextUp == "F" || nextUp == "S") {
                    return Pair(Pair(currentXPosition, currentYPosition-1), Pair(currentXPosition, currentYPosition))
                }
            } else if (lastYPosition == currentYPosition-1) {
                //goingRight
                var nextRight = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition+1)
                if (nextRight == "J" || nextRight == "-" || nextRight == "7" || nextRight == "S") {
                    return Pair(Pair(currentXPosition+1, currentYPosition), Pair(currentXPosition, currentYPosition))
                }
            }

        } else if (currentSymbol == "F") {
            //south-east
            if (lastXPosition == currentXPosition+1) {
                //goingDown
                var nextDown = twoDArray.getOrNull(currentYPosition+1)?.getOrNull(currentXPosition)
                if (nextDown == "J" || nextDown == "L" || nextDown == "|" || nextDown == "S") {
                    return Pair(Pair(currentXPosition, currentYPosition+1), Pair(currentXPosition, currentYPosition))
                }
            } else if (lastYPosition == currentYPosition+1) {
                //goingRight
                var nextRight = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition+1)
                if (nextRight == "J" || nextRight == "-" || nextRight == "7" || nextRight == "S") {
                    return Pair(Pair(currentXPosition+1, currentYPosition), Pair(currentXPosition, currentYPosition))
                }
            }


        } else if (currentSymbol == "7") {
            //south-west
            if (lastXPosition == currentXPosition-1) {
                //goingDown from left
                var nextDown = twoDArray.getOrNull(currentYPosition+1)?.getOrNull(currentXPosition)
                if (nextDown == "J" || nextDown == "L" || nextDown == "|" || nextDown == "S") {
                    return Pair(Pair(currentXPosition, currentYPosition+1), Pair(currentXPosition, currentYPosition))
                }
            } else if (lastYPosition == currentYPosition+1) {
                //goingLeft from down
                var nextLeft = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition-1)
                if (nextLeft == "-" || nextLeft == "L" || nextLeft == "F" || nextLeft == "S") {
                    return Pair(Pair(currentXPosition-1, currentYPosition), Pair(currentXPosition, currentYPosition))
                }
            }

        }
        return null
    }

    private fun findNextPossibleSymbol(
        twoDArray: Array<Array<String>>,
        currentXPosition: Int,
        currentYPosition: Int,
        currentSymbol: String
    ): Pair<Pair<Int, Int>, Pair<Int, Int>>? {
        if (currentSymbol == "|") {
            //north-south
            var nextUp = twoDArray.getOrNull(currentYPosition-1)?.getOrNull(currentXPosition)
                if (nextUp == "|" || nextUp == "7" || nextUp == "F") {
                    return Pair(Pair(currentXPosition, currentYPosition-1), Pair(currentXPosition, currentYPosition))
                } else {
                var nextDown = twoDArray.getOrNull(currentYPosition+1)?.getOrNull(currentXPosition)
                if (nextDown == "J" || nextDown == "L" || nextDown == "|") {
                    return Pair(Pair(currentXPosition, currentYPosition+1), Pair(currentXPosition, currentYPosition))
                }
            }
        } else if (currentSymbol == "-") {
            //west-east
            var nextLeft = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition-1)
            if (nextLeft == "-" || nextLeft == "L" || nextLeft == "F") {
                return Pair(Pair(currentXPosition-1, currentYPosition), Pair(currentXPosition, currentYPosition))
            } else {
                var nextRight = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition+1)
                if (nextRight == "J" || nextRight == "-" || nextRight == "7") {
                    return Pair(Pair(currentXPosition+1, currentYPosition), Pair(currentXPosition, currentYPosition))
                }
            }

        } else if (currentSymbol == "J") {
            //north-west
            var nextUp = twoDArray.getOrNull(currentYPosition-1)?.getOrNull(currentXPosition)
            if (nextUp == "|" || nextUp == "7" || nextUp == "F") {
                return Pair(Pair(currentXPosition, currentYPosition-1), Pair(currentXPosition, currentYPosition))
            } else {
                var nextLeft = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition-1)
                if (nextLeft == "-" || nextLeft == "L" || nextLeft == "F") {
                    return Pair(Pair(currentXPosition-1, currentYPosition), Pair(currentXPosition, currentYPosition))
                }
            }

        } else if (currentSymbol == "L") {
            //north-east
            var nextUp = twoDArray.getOrNull(currentYPosition-1)?.getOrNull(currentXPosition)
            if (nextUp == "|" || nextUp == "7" || nextUp == "F") {
                return Pair(Pair(currentXPosition, currentYPosition-1), Pair(currentXPosition, currentYPosition))
            } else {
                var nextRight = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition+1)
                if (nextRight == "J" || nextRight == "-" || nextRight == "7") {
                    return Pair(Pair(currentXPosition+1, currentYPosition), Pair(currentXPosition, currentYPosition))
                }
            }

        } else if (currentSymbol == "F") {
            //south-east
            var nextDown = twoDArray.getOrNull(currentYPosition+1)?.getOrNull(currentXPosition)
            if (nextDown == "J" || nextDown == "L" || nextDown == "|") {
                return Pair(Pair(currentXPosition, currentYPosition+1), Pair(currentXPosition, currentYPosition))
            } else {
                var nextRight = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition+1)
                if (nextRight == "J" || nextRight == "-" || nextRight == "7") {
                    return Pair(Pair(currentXPosition+1, currentYPosition), Pair(currentXPosition, currentYPosition))
                }
            }


        } else if (currentSymbol == "7") {
            //south-west
            var nextDown = twoDArray.getOrNull(currentYPosition-1)?.getOrNull(currentXPosition)
            if (nextDown == "J" || nextDown == "L" || nextDown == "|") {
                return Pair(Pair(currentXPosition, currentYPosition-1), Pair(currentXPosition, currentYPosition))
            } else {
                var nextLeft = twoDArray.getOrNull(currentYPosition)?.getOrNull(currentXPosition+1)
                if (nextLeft == "-" || nextLeft == "L" || nextLeft == "F") {
                    return Pair(Pair(currentXPosition-1, currentYPosition), Pair(currentXPosition, currentYPosition))
                }
            }

        }
        return null
    }

    private fun goalFound(xPosition: Int, yPosition: Int, xPosition1: Int, yPosition1: Int, maxDistance: Long): Boolean {
            return (xPosition == xPosition1 && yPosition == yPosition1) && maxDistance > 0
    }


    fun solvePart2Day10(filePath: String): Long {

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