package advent2024

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day01Test {

    @Test
    fun puzzle1Test() {

        val day01 = Day01()
        val res = day01.puzzle1("advent2024/Day01/test-input.txt")
        assertEquals(11, res)
    }

    @Test
    fun puzzle1Input() {

        val day01 = Day01()
        val res = day01.puzzle1("advent2024/Day01/input1.txt")
        assertEquals(1579939, res)
    }

    @Test
    fun puzzle2Test() {

        val day01 = Day01()
        val res = day01.puzzle2("advent2024/Day01/test-input.txt")
        assertEquals(31, res)
    }

    @Test
    fun puzzle2Input() {

        val day01 = Day01()
        val res = day01.puzzle2("advent2024/Day01/input1.txt")
        assertEquals(20351745, res)
    }



}