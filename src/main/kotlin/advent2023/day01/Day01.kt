package advent2023.day01

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


class Day01 {

    fun solvePart1Day01(filePath: String): Int {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var line: String?
                var sum = 0
                while (bufferedReader.readLine().also { line = it } != null) {
                    var first = 0
                    var last = 0
                    var lineAsList = line!!.split("")
                    lineAsList.forEach { s ->
                        s.toIntOrNull()?.let {number ->
                            if (first == 0) {
                                first = number
                            }
                            last = number
                        }
                    }
                    sum += ("$first$last").toInt()
                }
                return sum

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    fun solvePart2Day01(filePath: String): Int {

        val list = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var line: String?
                var sum = 0
                while (bufferedReader.readLine().also { line = it } != null) {
                    var first = 0
                    var last = 0
                    var lineAsList = line!!.split("")
                    lineAsList.forEachIndexed { i, s ->
                        var index = i
                        var tmp = s
                        if (tmp.toIntOrNull() == null) {
                            var found = 0
                            if (list.any { start -> s.startsWith(start.first.first()) }) {
                                var tempString = ""
                                var currentTmpString = lineAsList.get(index)
                                while ((currentTmpString.toIntOrNull() == null) && (list.none { pair -> pair.first == tempString })) {
                                    tempString += currentTmpString
                                    if (index == lineAsList.lastIndex) {
                                        break
                                    }
                                    index += 1
                                    currentTmpString = lineAsList.get(index)
                                }
                                list.forEach { pair ->
                                    if (pair.first == tempString) {
                                        found = pair.second
                                        if (first == 0) {
                                            first = found
                                        }
                                        last = found
                                    }
                                }
                            }
                        } else {
                            s.toInt().let {number ->
                                if (first == 0) {
                                    first = number
                                }
                                last = number
                            }
                        }

                    }
                    sum += ("$first$last").toInt()
                }
                return sum

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }
}