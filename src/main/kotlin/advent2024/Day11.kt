package advent2024

import utils.BasePuzzle
import kotlin.math.pow

class Day11 : BasePuzzle() {
    override fun puzzle1(filePath: String): Long {
        val input = getInput(filePath)
        var stones = input[0].split(" ").mapNotNull { it.toLongOrNull() }.toMutableList()
        for (i in 0 until 25) {
            stones = blinkOnceList(stones)
        }
        return stones.size.toLong()
    }

    override fun puzzle2(filePath: String): Any {
        val input = getInput(filePath)
        var stones = input[0].split(" ").mapNotNull { it.ifEmpty { null } }.toMutableList()
//        return blinkIterative(stones)

        var stoneMap = mutableMapOf(0 to stones)
        var res = 0L
        for (stone in stones) {
            res += blinkOnceRec(stone, 75)
        }

        return res
    }

    fun blinkIterative(stones: List<Long>): Long {
        var res = 0L
        val queue = ArrayDeque<Pair<Long, Int>>()

        // Initialisiere die Queue mit den Startsteinen
        stones.forEach { stone -> queue.add(Pair(stone, 0)) }

        while (queue.isNotEmpty()) {
            val (currentStone, depth) = queue.removeFirst()

            if (depth == 75) {
                res++
            } else {
                val newStones = applyRulesToStone(currentStone)
                newStones.forEach { queue.add(Pair(it, depth + 1)) }
            }
        }

        return res
    }

    var memo = mutableMapOf<String, Long>()
    private fun blinkOnceRec(stone: String, times: Int): Long {
        if (times == 0) return 1L
        val key = "$stone,$times"
        val found = memo[key]
        if (found != null) return found

        val newValue = when {
            //Strings da stone.tosString() --> Heap Space
            stone == "0" -> return blinkOnceRec("1", times-1)
            stone.length % 2 == 0 ->
                blinkOnceRec(stone.substring(0, stone.length/2).toLong().toString(), times-1) +
                        blinkOnceRec(stone.substring(stone.length/2, stone.length).toLong().toString(), times-1)
            else -> blinkOnceRec((stone.toLong() * 2024).toString(), times-1)
        }
        memo[key] = newValue
        return newValue
    }

    private fun blinkOnceList(stones: MutableList<Long>): MutableList<Long> {
        var newStones = mutableListOf<Long>()
        for (stone in stones) {
            newStones.addAll(applyRulesToStone(stone))
        }
        return newStones
    }

    private fun applyRulesToStone(stone: Long): MutableList<Long> {
        if (stone == 0L) {
            return mutableListOf(1L)
        } else if (stone.toString().length % 2 == 0) {
            val tmp = stone.toString()
            val middle = tmp.length / 2
            val p1 = tmp.substring(0, middle).toLong()
            val p2 = tmp.substring(middle).toLong()
            return mutableListOf(p1, p2)
        } else {
            return mutableListOf(stone * 2024L)
        }
    }
}