package advent2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.TestPuzzle

class Day11Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day = Day11()
        val res = day.puzzle1("advent2024/Day11/test-input.txt")
        assertEquals(55312L, res)
    }

    @Test
    override fun puzzle1Input() {
        val day = Day11()
        val res = day.puzzle1("advent2024/Day11/input.txt")
        assertEquals(203609L, res)
    }

    @Test
    override fun puzzle2Test() {
        val day = Day11()
        val res = day.puzzle2("advent2024/Day11/test-input.txt")
        assertEquals(118601L, res)
    }

    @Test
    override fun puzzle2Input() {
        val day = Day11()
        val res = day.puzzle2("advent2024/Day11/input.txt")
        assertEquals(240954878211138L, res)
    }
}