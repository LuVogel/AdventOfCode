package advent2024

import utils.TestPuzzle
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day05 = Day05()
        val res = day05.puzzle1("advent2024/Day05/test-input.txt")
        assertEquals(143, res)
    }

    @Test
    override fun puzzle1Input() {
        val day05 = Day05()
        val res = day05.puzzle1("advent2024/Day05/input1.txt")
        assertEquals(5651, res)
    }

    @Test
    override fun puzzle2Test() {
        val day05 = Day05()
        val res = day05.puzzle2("advent2024/Day05/test-input.txt")
        assertEquals(123, res)
    }

    @Test
    override fun puzzle2Input() {
        val day05 = Day05()
        val res = day05.puzzle2("advent2024/Day05/input1.txt")
        assertEquals(4743, res)
    }
}