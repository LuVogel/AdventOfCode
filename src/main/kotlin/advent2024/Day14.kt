package advent2024

import utils.BasePuzzle

class Day14 : BasePuzzle() {


    data class Position(val x: Int, val y: Int)
    data class Vector(val x: Int, val y: Int)
    data class Roboter(var position: Position, val vector: Vector)

    fun testInput(filePath: String): Long {
        return solvePuzzle(filePath, 11, 7)
    }

    override fun puzzle1(filePath: String): Any {
       return solvePuzzle(filePath, 101, 103)
    }

    override fun puzzle2(filePath: String): Any {
        val input = getInput(filePath)
        val roboterMap= mutableMapOf<Position, MutableList<Roboter>>()
        val height = 103
        val width = 101
        var i = height - 1
        val christmasTree = mutableListOf<Position>()
        val possibleMaps = mutableMapOf<Int, MutableMap<Position, MutableList<Roboter>>>()
        for (i in 0 until height) {
            val centerX = width / 2
            val startX = maxOf(0, centerX - i)
            val endX = minOf(width - 1, centerX + i)

            for (x in startX..endX) {
                christmasTree.add(Position(x, i))
            }
        }
        var k =0
        while (k < 1000) {
            for (line in input) {
                val roboter = getRoboterFromLine(line)
                var tmpX = (roboter.position.x + roboter.vector.x)
                var tmpY = (roboter.position.y + roboter.vector.y )
                if (tmpX >= width) {
                    tmpX -= width
                }
                if (tmpY >= height) {
                    tmpY -= height
                }
                if (tmpY < 0) {
                    tmpY += height
                }
                if (tmpX < 0) {
                    tmpX += width
                }
                roboter.position = Position(tmpX, tmpY)
                roboterMap.getOrPut(key = roboter.position) { mutableListOf() }.add(roboter)
                val tempList = mutableListOf<String>()
                for (i in input.indices) {
                    var tmpS = ""
                    for (j in input[i].indices) {
                        if (Position(j, i) in roboterMap) {
                            tmpS += "1"
                        } else {
                            tmpS += "0"
                        }
                    }
                    tempList.add(tmpS)
                }
                var tmpS = ""
                for (i in 0 until width) {
                    tmpS += "1"
                }
//                if (tempList.contains(tmpS)) {
//                    return i
//                }
                if (roboterMap.values.size == roboterMap.values.toSet().size) {
                    possibleMaps.put(k, roboterMap)
                }
                k++

//                val tempList = mutableListOf<Position>()
//                for (x in 0 until width) {
//                    tempList.add(Position(x, height-1))
//                }
//                if (christmasTree.all { pos -> roboterMap.contains(pos) } || (tempList.all { pos -> roboterMap.contains(pos) })) {
//                    return i
//                }
            }
        }
        return k
    }


    private fun solvePuzzle(filePath: String, width: Int, height: Int): Long {
        val input = getInput(filePath)
        val roboterMap1= mutableMapOf<Position, MutableList<Roboter>>()
        val roboterMap2= mutableMapOf<Position, MutableList<Roboter>>()
        val roboterMap3= mutableMapOf<Position, MutableList<Roboter>>()
        val roboterMap4= mutableMapOf<Position, MutableList<Roboter>>()
        for (line in input) {
            val roboter = getRoboterFromLine(line)
            if (roboter.position.x == 2 && roboter.position.y == 4) {
                println()
            }
            for (i in 0 until 100) {
                var tmpX = (roboter.position.x + roboter.vector.x)
                var tmpY = (roboter.position.y + roboter.vector.y )
                if (tmpX >= width) {
                    tmpX -= width
                }
                if (tmpY >= height) {
                    tmpY -= height
                }
                if (tmpY < 0) {
                    tmpY += height
                }
                if (tmpX < 0) {
                    tmpX += width
                }
                roboter.position = Position(tmpX, tmpY)
            }
            if (roboter.position.x != width / 2 && roboter.position.y != height / 2) {
                if (roboter.position.x in 0 until width / 2 && roboter.position.y in 0 until height / 2) {
                    //first quadrant
                    roboterMap1.getOrPut(key = roboter.position) { mutableListOf() }.add(roboter)
                } else if (roboter.position.x in 0 until width / 2 && roboter.position.y in height / 2 + 1 until height) {
                    //first second
                    roboterMap2.getOrPut(key = roboter.position) { mutableListOf() }.add(roboter)
                } else if (roboter.position.x in width / 2 + 1 until width && roboter.position.y in height / 2 + 1 until height) {
                    //third second
                    roboterMap3.getOrPut(key = roboter.position) { mutableListOf() }.add(roboter)
                } else if (roboter.position.x in width / 2 + 1 until width && roboter.position.y in 0 until height / 2) {
                    //fourth second
                    roboterMap4.getOrPut(key = roboter.position) { mutableListOf() }.add(roboter)
                }
            }
        }
        var res = 1L
        val robotMaps = listOf(roboterMap1, roboterMap2, roboterMap3, roboterMap4)
        var i = 0
        for (robotMap in robotMaps) {
            var sum = 0L
            println("quadrant: $i")
            i++
            for ((position, robots) in robotMap) {
                sum += robots.size
            }
            if (sum != 0L) {
                res *= sum
            }
        }
        return res
    }


    private fun getRoboterFromLine(line: String): Roboter {
        val tmp = line.split(" ")
        val tmp1 = tmp[0].substringAfter("=").split(",")
        val position = Position(tmp1[0].toInt(), tmp1[1].toInt())
        val tmp2 = tmp[1].substringAfter("=").split(",")
        val vector = Vector(tmp2[0].toInt(), tmp2[1].toInt())
        return Roboter(position, vector)
    }
}