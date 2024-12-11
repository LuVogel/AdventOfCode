package advent2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.TestPuzzle

class Day11Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day = Day11()
        val res = day.puzzle1("advent2024/Day11/test-input.txt")
        assertEquals(36, res)
    }

    @Test
    override fun puzzle1Input() {
        val day = Day11()
        val res = day.puzzle1("advent2024/Day11/input.txt")
        assertEquals(36, res)
    }

    @Test
    override fun puzzle2Test() {
        val day = Day11()
        val res = day.puzzle2("advent2024/Day11/test-input.txt")
        assertEquals(36, res)
    }

    @Test
    override fun puzzle2Input() {
        val day = Day11()
        val res = day.puzzle2("advent2024/Day11/input.txt")
        assertEquals(36, res)
    }
}