package advent2023.day08

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


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
                var resultList : MutableList<String> = mutableListOf()
                for (start in map.keys) {
                    if (start.endsWith("A")) {
                        resultList.add(start)
                    }
                }
                var counter: Long = 0
                while (!resultList.all { it.endsWith("Z") }) {
                    for (i in instructions.split("")) {
                        if (i != "") {
                            if (i == "R") {
                                counter += 1
                                resultList.replaceAll{ it ->
                                    map[it]?.second ?: it
                                }
                            }else if (i == "L") {
                                counter += 1
                                resultList.replaceAll{ it ->
                                    map[it]?.first ?: it
                                }
                            }

                            if (resultList.all { it.endsWith("Z")} ) {
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


}