package advent2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.TestPuzzle

class Day10Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day10 = Day10()
        val res = day10.puzzle1("advent2024/Day10/test-input.txt")
        assertEquals(36, res)
    }
    @Test
    override fun puzzle1Input() {
        val day10 = Day10()
        val res = day10.puzzle1("advent2024/Day10/input1.txt")
        assertEquals(611, res)
    }
    @Test
    override fun puzzle2Test() {
        val day10 = Day10()
        val res = day10.puzzle2("advent2024/Day10/test-input.txt")
        assertEquals(81, res)
    }
    @Test
    override fun puzzle2Input() {
        val day10 = Day10()
        val res = day10.puzzle2("advent2024/Day10/input1.txt")
        assertEquals(1380, res)
    }
}