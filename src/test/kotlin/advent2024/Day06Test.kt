package advent2024

import utils.TestPuzzle
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day06 = Day06()
        val res = day06.puzzle1("advent2024/Day06/test-input.txt")
        assertEquals(41, res)
    }

    @Test
    override fun puzzle1Input() {
        val day06 = Day06()
        val res = day06.puzzle1("advent2024/Day06/input1.txt")
        assertEquals(4903, res)
    }

    @Test
    override fun puzzle2Test() {
        val day06 = Day06()
        val res = day06.puzzle2("advent2024/Day06/test-input.txt")
        assertEquals(6, res)
    }

    @Test
    override fun puzzle2Input() {
        val day06 = Day06()
        val res = day06.puzzle2("advent2024/Day06/input1.txt")
        assertEquals(4903, res)
    }
}