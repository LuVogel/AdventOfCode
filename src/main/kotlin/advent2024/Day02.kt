package advent2024

import utils.BasePuzzle
import kotlin.math.abs

class Day02 : BasePuzzle() {


    private fun bothParts(filePath : String) : Pair<Int, Int> {
        val input = getInput(filePath)
        var res1 = 0
        var res2 = 0
        for (line in input) {
            val numberList = line.split(" ")
            val first = numberList[0].toInt()
            val second = numberList[1].toInt()
            if (first > second) {
                if (checkDecreasing(numberList)) {
                    res1 += 1
                    res2 += 1
                } else if (checkForSingleBadLevel(numberList)) {
                    res2 += 1
                }
            } else if (first < second) {
                if (checkIncreasing(numberList)) {
                    res1 += 1
                    res2 += 1
                } else if (checkForSingleBadLevel(numberList)) {
                    res2 += 1
                }
            } else if (checkForSingleBadLevel(numberList)) {
                res2 += 1
            }
        }
        return Pair(res1, res2)
    }

    override fun puzzle1(filePath: String): Any {
        return bothParts(filePath).first
    }

    private fun checkForSingleBadLevel(numberList: List<String>) : Boolean {
        for (index in numberList.indices) {
            val tempList = numberList.toMutableList()
            tempList.removeAt(index)
            if (checkDecreasing(tempList) || checkIncreasing(tempList)) {
                return true
            }
        }
        return false;
    }

    private fun checkDecreasing(numberList: List<String>) : Boolean {
        var currentNum = numberList[0].toInt()
        for (i in 1 until numberList.size) {
            if (currentNum <= numberList[i].toInt()) {
                return false
            } else if (abs((currentNum - numberList[i].toInt())) > 3) {
                return false
            }
            currentNum = numberList[i].toInt()
        }
        return true
    }

    private fun checkIncreasing(numberList: List<String>) : Boolean {
        var currentNum = numberList[0].toInt()
        for (i in 1 until numberList.size) {
            if (currentNum >= numberList[i].toInt()) {
                return false
            } else if (abs((currentNum - numberList[i].toInt())) > 3) {
                return false
            }
            currentNum = numberList[i].toInt()
        }
        return true
    }

    override fun puzzle2(filePath: String): Any {
        return bothParts(filePath).second
    }

}