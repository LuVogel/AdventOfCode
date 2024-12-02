package advent2024

import utils.BasePuzzle
import utils.HelperUtils
import java.io.File
import kotlin.math.abs

class Day01 : BasePuzzle(){


    override fun puzzle1(filePath: String) : Long {
        val (list1, list2) = getList(filePath)
        var res = 0L
        for (i in list1.indices) {
            res += abs(list1[i] - list2[i])
        }
        return res
    }

    override fun puzzle2(filePath: String) : Long {
       val (list1, list2) = getList(filePath)
        var res = 0L
        for (num in list1) {
            val count = list2.count { it == num }
            res += (num * count)
        }
        return res
    }

    fun getList(filePath: String) : Pair<MutableList<Long>, MutableList<Long>> {
        val input = getInput(filePath)
        val list1 = mutableListOf<Long>()
        val list2 = mutableListOf<Long>()
        for (line in input) {
            val first = line.substringBefore(" ").toLong()
            val second =line.substringAfterLast(" ").toLong()
            list1.add(first)
            list2.add(second)
        }
        list1.sort()
        list2.sort()
        return Pair(list1, list2)
    }
}
