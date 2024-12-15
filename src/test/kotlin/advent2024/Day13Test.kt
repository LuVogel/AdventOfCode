package advent2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.TestPuzzle

class Day13Test: TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day = Day13()
        val res = day.puzzle1("advent2024/Day13/test-input.txt")
        assertEquals(480, res)
    }

    @Test
    override fun puzzle1Input() {
        val day = Day13()
        val res = day.puzzle1("advent2024/Day13/input.txt")
        assertEquals(35997, res)
    }

    @Test
    override fun puzzle2Test() {
        val day = Day13()
        val res = day.puzzle2("advent2024/Day13/test-input.txt")
        assertEquals(80, res)
    }

    @Test
    override fun puzzle2Input() {
        val day = Day13()
        val res = day.puzzle2("advent2024/Day13/input.txt")
        assertEquals(80, res)
    }
}