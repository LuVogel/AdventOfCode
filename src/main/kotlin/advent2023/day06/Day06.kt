package advent2023.day06

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


class Day06 {

    fun solvePart1Day06(filePath: String): Int {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                var time : MutableList<Int> = mutableListOf()
                var distance : MutableList<Int> = mutableListOf()
                while (bufferedReader.readLine().also { lines = it } != null) {
                    if (lines!!.startsWith("Time")) {
                        var split = lines!!.split(": ")[1].split(" ")
                        for (s in split) {
                            if (s.toIntOrNull() != null) {
                                time.add(s.toInt())
                            }
                        }
                    } else {
                        var split = lines!!.split(": ")[1].split(" ")
                        for (s in split) {
                            if (s.toIntOrNull() != null) {
                                distance.add(s.toInt())
                            }
                        }
                    }
                }
                var result = 1
                for (i in 0 until time.size) {
                    var currentTime = time[i]
                    var currentDistance = distance[i]
                    var counter = 0
                    for (j in 1 until currentTime) {
                        var traveledDistance = j * (currentTime-j)
                        if (traveledDistance > currentDistance) {
                            counter += 1
                        }
                    }
                    if (counter != 0) {
                        result *= counter
                    }
                }
                return result

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }



    fun solvePart2Day06(filePath: String): Long {
        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                var timeS = ""
                var distanceS = ""
                while (bufferedReader.readLine().also { lines = it } != null) {
                    if (lines!!.startsWith("Time")) {
                        var split = lines!!.split(": ")[1].split(" ")
                        for (s in split) {
                            if (s.toIntOrNull() != null) {
                                timeS += s
                            }
                        }
                    } else {
                        var split = lines!!.split(": ")[1].split(" ")
                        for (s in split) {
                            if (s.toIntOrNull() != null) {
                                distanceS += s
                            }
                        }
                    }
                }
                var time = timeS.toLong()
                var distance = distanceS.toLong()
                var counter : Long = 0
                for (j in 1 until time) {
                    var traveledDistance = j * (time - j)
                    if (traveledDistance > distance) {
                        counter += 1
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