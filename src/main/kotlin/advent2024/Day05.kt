package advent2024

import utils.BasePuzzle

class Day05 : BasePuzzle() {
    override fun puzzle1(filePath: String): Any {
        val (ordered, unordered, orderRules) = getOrderedAndUnorderedLists(filePath)
        var sum = 0
        for (order in ordered) {
            sum += order[order.size / 2]
        }
        return sum
    }

    override fun puzzle2(filePath: String): Any {
        val (ordered, unordered, orderRules) = getOrderedAndUnorderedLists(filePath)
        var sum = 0
        for (unorder in unordered) {
            for (i in unorder.indices) {
                val currentPage = unorder[i]
                for (j in 0 until i) {
                    val orderList = orderRules[currentPage]
                    if (!orderList.isNullOrEmpty() ) {
                        if (orderList.contains(unorder[j])) {
                            //swap
                            val tmp = unorder[j]
                            unorder[j] = unorder[i]
                            unorder[i] = tmp
                        }
                    }
                }
            }
            sum += unorder[unorder.size / 2]
        }
        return sum
    }


    private fun getOrderedAndUnorderedLists(filePath: String): Triple<MutableList<MutableList<Int>>, MutableList<MutableList<Int>>, MutableMap<Int, MutableList<Int>>> {
        val input = getInput(filePath)
        var updates = mutableListOf<MutableList<Int>>()
        var orderRules = mutableMapOf<Int, MutableList<Int>>()
        for (line in input) {
            if (line.contains("|")) {
                val (first, second) = line.split("|")
                orderRules.getOrPut(key = first.toInt()) { mutableListOf() }.add(second.toInt())
            }
            if (line.contains(",")) {
                var update = mutableListOf<Int>()
                for (s in line.split(",")) {
                    update.add(s.toInt())
                }
                updates.add(update)
            }
        }
        var orderedList = mutableListOf<MutableList<Int>>()
        var unorderedList = mutableListOf<MutableList<Int>>()
        for (update in updates) {
            var ordered = true
            for (i in update.indices) {
                val currentPage = update[i]
                for (j in i until update.size) {
                    val orderList = orderRules[update[j]]
                    if (!orderList.isNullOrEmpty() ) {
                        if (orderList.contains(currentPage)) {
                            ordered = false
                        }
                    }
                }
            }
            if (ordered) {
                orderedList.add(update)

            } else {
                unorderedList.add(update)
            }
        }
        return Triple(orderedList, unorderedList, orderRules)
    }
}