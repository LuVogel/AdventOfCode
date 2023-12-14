package advent2023.day09

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


class Day09 {


    fun solvePart1Day09(filePath: String): Long {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                var sumOfHistories : Long = 0
                while (bufferedReader.readLine().also { lines = it } != null) {
                    var digitList : MutableList<Long> = mutableListOf()
                    for (digit in lines!!.split(" ")) {
                        if (digit.toLongOrNull() != null) {
                            digitList.add(digit.toLong())
                        }
                    }
                    var paramList : MutableList<Long> = getParameters1(digitList.toMutableList())
                    var history = 0L
                    for (i in paramList.size-1 downTo 0) {
                        var currentHistory = paramList[i]
                        if (i != paramList.size-1) {
                            history += currentHistory
                        }
                    }
                    sumOfHistories += history
                }

                return sumOfHistories
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    private fun getParameters1(list : MutableList<Long>): MutableList<Long> {
        var resList: MutableList<Long> = mutableListOf(list.last())
        var digitList = list.toMutableList()
        while (digitList.any { it != 0L }) {
            var tempList : MutableList<Long> = mutableListOf()
            for (i in 0 until digitList.size-1) {
                var startDigit = digitList[i]
                var endDigit = digitList[i+1]
                var diff = endDigit - startDigit
                tempList.add(diff)
            }
            digitList = tempList.toMutableList()
            resList.add(tempList.last())
        }
        digitList.clear()
        return resList
    }

    private fun getParameters2(list : MutableList<Long>): MutableList<Long> {
        var resList: MutableList<Long> = mutableListOf(list.first())
        var digitList = list.toMutableList()
        while (digitList.any { it != 0L }) {
            var tempList : MutableList<Long> = mutableListOf()
            for (i in 0 until digitList.size-1) {
                var startDigit = digitList[i]
                var endDigit = digitList[i+1]
                var diff = endDigit - startDigit
                tempList.add(diff)
            }
            digitList = tempList.toMutableList()
            resList.add(tempList.first())
        }
        digitList.clear()
        return resList


    }
    fun solvePart2Day09(filePath: String): Long {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                var sumOfHistories: Long = 0
                while (bufferedReader.readLine().also { lines = it } != null) {
                    var digitList: MutableList<Long> = mutableListOf()
                    for (digit in lines!!.split(" ")) {
                        if (digit.toLongOrNull() != null) {
                            digitList.add(digit.toLong())
                        }
                    }
                    var paramList: MutableList<Long> = getParameters2(digitList.toMutableList())
                    var history = 0L
                    for (i in paramList.size-1 downTo 0) {
                        var currentHistory = paramList[i]
                        if (i != paramList.size-1) {
                            history = currentHistory - history
                        }
                    }
                    sumOfHistories += history
                }

                return sumOfHistories
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0

    }
}