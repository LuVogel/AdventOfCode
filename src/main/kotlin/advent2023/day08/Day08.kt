package advent2023.day08

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.lang.StringBuilder


class Day08 {


    fun solvePart1Day08(filePath: String): Long {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                var instructions = ""
                var map: MutableMap<String, Pair<String, String>> = mutableMapOf()
                while (bufferedReader.readLine().also { lines = it } != null) {
                    if (lines!!.isNotEmpty() && !lines!!.contains("=")) {
                        //instruction
                        instructions += lines
                    } else if (lines!!.isNotEmpty()) {
                        var split = lines!!.split(" = ")
                        var leftSide = split[0]
                        var rightSplit = split[1].split("(")[1].split(")")[0].split(", ")
                        map[leftSide] = Pair(rightSplit[0], rightSplit[1])
                    }
                }
                var result = "AAA"
                var counter: Long = 0
                while (result != "ZZZ") {
                    for (i in instructions.split("")) {
                        if (i != "") {
                            if (i == "R") {
                                counter += 1
                                result = map[result]!!.second
                            }else if (i == "L") {
                                counter += 1
                                result = map[result]!!.first
                            }
                            if (result == "ZZZ") {
                                return counter
                            }
                        }

                    }
                }
                return counter
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    fun solvePart2Day08(filePath: String): Long {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                var instructions = StringBuilder()
                var map: MutableMap<String, Pair<String, String>> = mutableMapOf()
                while (bufferedReader.readLine().also { lines = it } != null) {
                    if (lines!!.isNotEmpty() && !lines!!.contains("=")) {
                        //instruction
                        instructions.append(lines)
                    } else if (lines!!.isNotEmpty()) {
                        var (leftside, rightside) = lines!!.split(" = ")
                        var (first, second) = rightside.split("(")[1].split(")")[0].split(", ")
                        map[leftside] = Pair(first, second)
                    }
                }
                var resultList : MutableList<String> = map.keys.filter { it.endsWith("A") }.toMutableList()
                var instructionChars = instructions.toString().toList()
                var counter: Long = 0
                while (!resultList.all { it.endsWith("Z") }) {
                    val previousList = resultList.toList()
                    instructionChars.forEach { i ->
                        when (i) {
                            'R' -> resultList.replaceAll{ map[it]?.second ?: it}
                            'L' -> resultList.replaceAll{ map[it]?.first ?: it}

                        }
                        counter++
                        println(counter)
                    }
                    if (previousList == resultList) {
                        break
                    }
                    if (resultList.all { it.endsWith("Z") }) {
                        return counter
                    }
                }
                return if (resultList.all { it.endsWith("Z") }) counter else -1
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }


}