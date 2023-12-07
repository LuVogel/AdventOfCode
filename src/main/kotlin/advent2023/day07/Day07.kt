package advent2023.day07

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


class Day07 {

    private val order1 = "23456789TJQKA"
    private var counter1 = 1
    private var result1: Long = 0
    private val order2 = "J23456789TQKA"
    private var counter2 = 1
    private var result2: Long = 0

    fun solvePart1Day07(filePath: String): Long {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                var fiveOfKind : MutableMap<String, Long> = mutableMapOf()
                var fourOfKind : MutableMap<String, Long> = mutableMapOf()
                var fullHouse : MutableMap<String, Long> = mutableMapOf()
                var threeOfKind : MutableMap<String, Long> = mutableMapOf()
                var twoPair : MutableMap<String, Long> = mutableMapOf()
                var onePair : MutableMap<String, Long> = mutableMapOf()
                var highCard : MutableMap<String, Long> = mutableMapOf()

                while (bufferedReader.readLine().also { lines = it } != null) {
                    var cards = lines!!.split(" ")[0]
                    var numb = lines!!.split(" ")[1]
                    var countList : MutableList<Int> = mutableListOf()
                    for (o in order1.split("")) {
                        countList.add(cards.count { it.toString() == o })
                    }
                    if (countList.contains(5)) {
                        fiveOfKind[cards] = numb.toLong()
                    } else if (countList.contains(4)) {
                        fourOfKind[cards] = numb.toLong()
                    } else if (countList.contains(3) && countList.contains(2)) {
                        fullHouse[cards] = numb.toLong()
                    } else if (countList.contains(3)) {
                        threeOfKind[cards] = numb.toLong()
                    } else if (countList.count { it == 2} == 2) {
                        twoPair[cards] = numb.toLong()
                    } else if (countList.contains(2)) {
                        onePair[cards] = numb.toLong()
                    } else {
                        highCard[cards] = numb.toLong()
                    }
                }

                if (highCard.isNotEmpty()) {
                    addFromMapToResult(highCard)
                    highCard.clear()
                }
                if (onePair.isNotEmpty()) {
                    addFromMapToResult(onePair)
                    onePair.clear()
                }
                if (twoPair.isNotEmpty()) {
                    addFromMapToResult(twoPair)
                    twoPair.clear()
                }
                if (threeOfKind.isNotEmpty()) {
                    addFromMapToResult(threeOfKind)
                    threeOfKind.clear()
                }
                if (fullHouse.isNotEmpty()) {
                    addFromMapToResult(fullHouse)
                    fullHouse.clear()
                }
                if (fourOfKind.isNotEmpty()) {
                    addFromMapToResult(fourOfKind)
                    fourOfKind.clear()
                }
                if (fiveOfKind.isNotEmpty()) {
                    addFromMapToResult(fiveOfKind)
                    fiveOfKind.clear()
                }

                return result1
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    fun compareStringsbyOrder(s1: String, s2: String, order: String): Int {
        val minLength = minOf(s1.length, s2.length)
        for (i in 0 until minLength) {
            var char1 = s1[i]
            var char2 = s2[i]
            var index1 = order.indexOf(char1)
            var index2 = order.indexOf(char2)
            if (index1 != index2) {
                return when {
                    index1 > index2 -> 1
                    index2 > index1 -> -1
                    else -> 0
                }.also { if (index1 == -1 || index2 == -1) println("error in index comparing") }
            }
        }
        return s1.length - s2.length
    }

    fun addFromMapToResult(map: MutableMap<String, Long>) {
        var sortedMap = map.entries.sortedWith(Comparator{ o1, o2 ->
            compareStringsbyOrder(
                o1.key,
                o2.key,
                order1) })
        for (card in sortedMap) {
            result1 += card.value * counter1
            counter1 += 1
        }
    }
    fun addFromMapToResult2(map: MutableMap<String, Long>) {
        var sortedMap = map.entries.sortedWith(Comparator{ o1, o2 ->
            compareStringsbyOrder(
                o1.key,
                o2.key,
                order2) })
        for (card in sortedMap) {
            result2 += card.value * counter2
            counter2 += 1
        }
    }


    fun solvePart2Day07(filePath: String): Long {
        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                var fiveOfKind : MutableMap<String, Long> = mutableMapOf()
                var fourOfKind : MutableMap<String, Long> = mutableMapOf()
                var fullHouse : MutableMap<String, Long> = mutableMapOf()
                var threeOfKind : MutableMap<String, Long> = mutableMapOf()
                var twoPair : MutableMap<String, Long> = mutableMapOf()
                var onePair : MutableMap<String, Long> = mutableMapOf()
                var highCard : MutableMap<String, Long> = mutableMapOf()

                while (bufferedReader.readLine().also { lines = it } != null) {
                    var cards = lines!!.split(" ")[0]
                    var numb = lines!!.split(" ")[1]
                    var countList : MutableList<Int> = mutableListOf()
                    var actualCards = cards
                    if (cards.contains("J")) {
                        actualCards = findBestJokerCard(cards)
                    }
                    for (o in order2.split("")) {
                        countList.add(actualCards.count { it.toString() == o })
                    }
                    if (countList.contains(5)) {
                        fiveOfKind[cards] = numb.toLong()
                    } else if (countList.contains(4)) {
                        fourOfKind[cards] = numb.toLong()
                    } else if (countList.contains(3) && countList.contains(2)) {
                        fullHouse[cards] = numb.toLong()
                    } else if (countList.contains(3)) {
                        threeOfKind[cards] = numb.toLong()
                    } else if (countList.count { it == 2} == 2) {
                        twoPair[cards] = numb.toLong()
                    } else if (countList.contains(2)) {
                        onePair[cards] = numb.toLong()
                    } else {
                        highCard[cards] = numb.toLong()
                    }
                }

                if (highCard.isNotEmpty()) {
                    addFromMapToResult2(highCard)
                    highCard.clear()
                }
                if (onePair.isNotEmpty()) {
                    addFromMapToResult2(onePair)
                    onePair.clear()
                }
                if (twoPair.isNotEmpty()) {
                    addFromMapToResult2(twoPair)
                    twoPair.clear()
                }
                if (threeOfKind.isNotEmpty()) {
                    addFromMapToResult2(threeOfKind)
                    threeOfKind.clear()
                }
                if (fullHouse.isNotEmpty()) {
                    addFromMapToResult2(fullHouse)
                    fullHouse.clear()
                }
                if (fourOfKind.isNotEmpty()) {
                    addFromMapToResult2(fourOfKind)
                    fourOfKind.clear()
                }
                if (fiveOfKind.isNotEmpty()) {
                    addFromMapToResult2(fiveOfKind)
                    fiveOfKind.clear()
                }

                return result2

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    private fun findBestJokerCard(cards: String): String {
        var tempCards = ""
        var mostFoundChar = cards.filter { it != 'J' }
            .groupingBy { it }
            .eachCount()
            .maxByOrNull { it.value }
        if (mostFoundChar == null) {
            return "AAAAA"
        }
        for (c in cards) {
            if (c == 'J') {
                tempCards += mostFoundChar!!.key
            } else {
                tempCards += c
            }
        }
        return tempCards
    }


}