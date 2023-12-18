package advent2023.day12

import org.apache.commons.codec.digest.DigestUtils
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import kotlin.math.min

class Day12 {




    fun solvePart1Day12(filePath: String): Long {
        foundArrangements = 0L
        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                while (bufferedReader.readLine().also { lines = it } != null) {
                    var input = lines!!.split(" ")[0]
                    var numbers = lines!!.split(" ")[1]
                    findRearrangements(input, numbers)
                }
                return foundArrangements
            }
        } catch (e: IOException) {
            e.printStackTrace().also { print("error in try catch") }
        }
        return 0
    }

    private fun findRearrangements(input: String, numbers: String) {
        var numberList = numbers.split(",").filterNot { it.isBlank() || it.isEmpty()}
        var inputList = input.split("").filterNot { it.isBlank() || it.isEmpty() }.toMutableList()
        var currentGroupList : MutableList<String> = mutableListOf()
        findRecursive1(numberList, inputList, currentGroupList, "", 0)
        return
    }

    private fun checkAllGroups(inputList: MutableList<String>, numberList: List<String>): Boolean {
        var tmpList : MutableList<Int> = mutableListOf()
        var tmpS = ""
        for (s in inputList) {
            if (s == "#") {
                tmpS += "#"
            } else if (s == ".") {
                if (tmpS != "") {
                    tmpList.add(tmpS.length)
                    tmpS = ""
                }
            }
        }
        if (tmpS != "") {
            tmpList.add(tmpS.length)
        }
        if (tmpList.size != numberList.size) {
            return false
        }
        for (i in tmpList.indices) {
            if (tmpList[i] != numberList[i].toInt()) {
                return false
            }
        }
        return true

    }
    var foundArrangements = 0L

    private fun findRecursive1(
        numberList: List<String>,
        inputList: MutableList<String>,
        currentGroupList: MutableList<String>,
        group: String,
        counter: Int
    ) {

        var currentGroup = group
        if (inputList.all { it != "?" }) {
            if (checkAllGroups(inputList, numberList)) {
                foundArrangements += 1
                return
            } else {
                return
            }
        }
        if (inputList[counter] == "#") {
            currentGroup += inputList[counter]
            findRecursive1(numberList, inputList,currentGroupList, currentGroup, counter+1)
        } else if (inputList[counter] == ".") {
            if (currentGroup != "") {
                currentGroupList.add(currentGroup)
                findRecursive1(numberList, inputList, currentGroupList, "", counter+1)
            } else {
                findRecursive1(numberList, inputList, currentGroupList, currentGroup, counter+1)
            }
        } else if (inputList[counter] == "?") {
            var tmpList = inputList.toMutableList()
            tmpList[counter] = "#"
            findRecursive1(numberList, tmpList, currentGroupList, currentGroup, counter+1)
            tmpList[counter] = "."
            findRecursive1(numberList, tmpList, currentGroupList, currentGroup, counter+1)
        }

        return
    }

    private fun generateKey(numberList: List<String>, inputList: MutableList<String>, currentGroupList: MutableList<String>, group: String, counter: Int): Int {
        return (numberList.toString() +
                inputList.toString() +
                currentGroupList.toString() +
                group +
                counter.toString()
                ).hashCode()

    }



    
    private val memoizationCache = mutableMapOf<Int, Long>()

    fun solvePart2Day12(filePath: String): Long {
        foundArrangements = 0L
        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                while (bufferedReader.readLine().also { lines = it } != null) {
                    var input = lines!!.split(" ")[0]
                    var numbers = lines!!.split(" ")[1]
                    var newNumbers  = ""
                    var newInput = ""
                    for (i in 0 until 5) {
                        newInput += input
                        newNumbers += numbers
                        if (i != 4) {
                            newInput += "?"
                            newNumbers += ","
                        }
                    }
                    findRearrangements(newInput, newNumbers)
                }
                return foundArrangements
            }
        } catch (e: IOException) {
            e.printStackTrace().also { print("error in try catch") }
        }
        return 0
    }

    private fun findRecursive2(
        numberList: List<String>,
        inputList: MutableList<String>,
        currentGroupList: MutableList<String>,
        group: String,
        counter: Int
    ): Long {
        val key = generateKey(numberList, inputList, currentGroupList, group, counter)

        memoizationCache[key]?.let {
            if (it == 0L) {
                return 0
            } else if (it == 1L) {
                foundArrangements += 1
                return 1
            }
        }
        var currentGroup = group
        if (inputList.all { it != "?" }) {
            if (checkAllGroups(inputList, numberList)) {
                foundArrangements += 1
                memoizationCache[key] = 1
                return 1
            } else {
                memoizationCache[key] = 0
                return 0
            }
        }
//        if (checkCurrentGroup(inputList, numberList)) {
        //check if already violation is found
        if (inputList[counter] == "#") {
            currentGroup += inputList[counter]
            findRecursive2(numberList, inputList,currentGroupList, currentGroup, counter+1)
        } else if (inputList[counter] == ".") {
            if (currentGroup != "") {
                currentGroupList.add(currentGroup)
                findRecursive2(numberList, inputList, currentGroupList, "", counter+1)
            } else {
                findRecursive2(numberList, inputList, currentGroupList, currentGroup, counter+1)
            }
        } else if (inputList[counter] == "?") {
            var tmpList = inputList.toMutableList()
            tmpList[counter] = "#"
            findRecursive2(numberList, tmpList, currentGroupList, currentGroup, counter+1)
            tmpList[counter] = "."
            findRecursive2(numberList, tmpList, currentGroupList, currentGroup, counter+1)
        }

//        }
        memoizationCache[key] = 0
        return 0
    }
}


