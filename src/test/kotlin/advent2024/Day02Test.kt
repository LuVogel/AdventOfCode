package advent2024

import utils.TestPuzzle
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day = Day02()
        val res = day.puzzle1("advent2024/Day02/test-input.txt")
        assertEquals(2, res)
    }
    @Test
    override fun puzzle1Input() {
        val day = Day02()
        val res = day.puzzle1("advent2024/Day02/input1.txt")
        assertEquals(224, res)
    }

    @Test
    override fun puzzle2Test() {
        val day = Day02()
        val res = day.puzzle2("advent2024/Day02/test-input.txt")
        assertEquals(4, res)
    }
    @Test
    override fun puzzle2Input() {
        val day = Day02()
        val res = day.puzzle2("advent2024/Day02/input1.txt")
        assertEquals(293, res)
    }
}