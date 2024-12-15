package advent2024

import org.junit.jupiter.api.Assertions.assertEquals
import utils.TestPuzzle
import kotlin.test.Test

class Day14Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day = Day14()
        val res = day.testInput("advent2024/Day14/test-input.txt")
        assertEquals(12, res)
    }

    @Test
    override fun puzzle1Input() {
        val day = Day14()
        val res = day.puzzle1("advent2024/Day14/input.txt")
        assertEquals(229868730L, res)
    }

    @Test
    override fun puzzle2Test() {
        val day = Day14()
        val res = day.testInput("advent2024/Day14/test-input.txt")
        assertEquals(12, res)
    }

    @Test
    override fun puzzle2Input() {
        val day = Day14()
        val res = day.puzzle2("advent2024/Day14/input.txt")
        assertEquals(480L, res)
    }
}