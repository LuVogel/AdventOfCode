package advent2024

import utils.TestPuzzle
import kotlin.test.Test
import kotlin.test.assertEquals

class Day04Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day04 = Day04()
        val res = day04.puzzle1("advent2024/Day04/test-input.txt")
        assertEquals(18, res)
    }

    @Test
    override fun puzzle1Input() {
        val day04 = Day04()
        val res = day04.puzzle1("advent2024/Day04/input1.txt")
        assertEquals(2532, res)
    }

    @Test
    override fun puzzle2Test() {
        val day04 = Day04()
        val res = day04.puzzle2("advent2024/Day04/test-input.txt")
        assertEquals(9, res)
    }

    @Test
    override fun puzzle2Input() {
        val day04 = Day04()
        val res = day04.puzzle2("advent2024/Day04/input1.txt")
        assertEquals(1941, res)
    }
}