package advent2023.day07

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import kotlin.math.min


class Day07 {

    val order = "AKQJT98765432".reversed()

    fun solvePart1Day07(filePath: String): Int {

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
                    for (o in order.split("")) {
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
                    highCard = highCard.toSortedMap { o1, o2 ->
                        compareStringsbyOrder(
                            o1.first().toString(),
                            o2.first().toString()
                        )
                    }
                }
                if (onePair.isNotEmpty()) {
                    onePair = onePair.toSortedMap { o1, o2 ->
                        compareStringsbyOrder(
                            o1.first().toString(),
                            o2.first().toString()
                        )
                    }
                }
                if (twoPair.isNotEmpty()) {
                    twoPair = twoPair.toSortedMap { o1, o2 ->
                        compareStringsbyOrder(
                            o1.first().toString(),
                            o2.first().toString()
                        )
                    }
                }
                if (threeOfKind.isNotEmpty()) {
                    threeOfKind = threeOfKind.toSortedMap { o1, o2 ->
                        compareStringsbyOrder(
                            o1.first().toString(),
                            o2.first().toString()
                        )
                    }
                }
                if (fullHouse.isNotEmpty()) {
                    fullHouse = fullHouse.toSortedMap { o1, o2 ->
                        compareStringsbyOrder(
                            o1.first().toString(),
                            o2.first().toString()
                        )
                    }
                }
                if (fourOfKind.isNotEmpty()) {
                    fourOfKind = fourOfKind.toSortedMap { o1, o2 ->
                        compareStringsbyOrder(
                            o1.first().toString(),
                            o2.first().toString()
                        )
                    }
                }
                if (fiveOfKind.isNotEmpty()) {
                     fiveOfKind = fiveOfKind.toSortedMap { o1, o2 ->
                        compareStringsbyOrder(
                            o1.first().toString(),
                            o2.first().toString()
                        )
                    }
                }
                var counter = 1
                var result = 0
                print(10)

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    fun compareStringsbyOrder(s1: String, s2: String): Int {
        val minLength = minOf(s1.length, s2.length)
        for (i in 0 until minLength) {
            var char1 = s1[i]
            var char2 = s2[i]
            var index1 = order.indexOf(char1)
            var index2 = order.indexOf(char2)
            if (index1 != index2) {
                return (index1 - index2)
            }
        }
        return s1.length - s2.length
    }



    fun solvePart2Day07(filePath: String): Long {
        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var lines: String?
                while (bufferedReader.readLine().also { lines = it } != null) {

                }

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }


}