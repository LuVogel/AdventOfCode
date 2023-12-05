package advent2023.day04

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


class Day04 {

    fun solvePart1Day04(filePath: String): Int {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())

        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var line: String?
                var totalSum = 0
                while (bufferedReader.readLine().also { line = it } != null) {
                    var cardSum = 0
                    var splitToCardNum = line!!.split(": ")
                    var winningSplit = splitToCardNum[1].split(" | ")[0]
                    var mySplit = splitToCardNum[1].split(" | ")[1]
                    var winningNumbers = winningSplit.split(" ").toMutableList()
                    var myNumbers = mySplit.split(" ").toMutableList()
                    for (myNumber in myNumbers) {
                        if (myNumber.toIntOrNull() != null) {
                            if (myNumber in winningNumbers) {
                                if (cardSum == 0) {
                                    cardSum = 1
                                } else {
                                    cardSum *= 2
                                }
                            }
                        }

                    }
                    totalSum += cardSum

                }
                return totalSum
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }



    fun solvePart2Day04(filePath: String): Int {
        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())

        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var line: String?
                var totalSum = 0
                var copiesPerNumber : MutableMap<Int, Int> = mutableMapOf()
                while (bufferedReader.readLine().also { line = it } != null) {
                    var winningCounter = 0
                    var splitToCardNum = line!!.split(": ")
                    var gameAndCard = splitToCardNum[0].split(" ")
                    var cardNumber = 0
                    for (s in gameAndCard) {
                        if (s.toIntOrNull() != null) {
                            cardNumber = s.toInt()
                        }
                    }
                    if (copiesPerNumber[cardNumber] == null) {
                        copiesPerNumber[cardNumber] = 1
                    } else {
                        copiesPerNumber[cardNumber] = copiesPerNumber[cardNumber]!! + 1
                    }
                    var winningSplit = splitToCardNum[1].split(" | ")[0]
                    var mySplit = splitToCardNum[1].split(" | ")[1]
                    var winningNumbers = winningSplit.split(" ").toMutableList()
                    var myNumbers = mySplit.split(" ").toMutableList()
                    for (myNumber in myNumbers) {
                        if (myNumber.toIntOrNull() != null) {
                            if (myNumber in winningNumbers) {
                                winningCounter += 1
                            }
                        }
                    }
                    for (i in cardNumber+1 until cardNumber+winningCounter+1) {
                        if (copiesPerNumber[i] == null) {
                            copiesPerNumber[i] = copiesPerNumber[cardNumber]!!
                        } else {
                            copiesPerNumber[i] = copiesPerNumber[i]!! + copiesPerNumber[cardNumber]!!

                        }
                    }
                }
                for (copies in copiesPerNumber.values) {
                    totalSum += copies
                }
                return totalSum
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

}