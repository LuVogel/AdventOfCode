package advent2024

import org.junit.jupiter.api.Assertions.assertEquals
import utils.TestPuzzle
import kotlin.test.Test

class Day08Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day08 = Day08()
        val res = day08.puzzle1("advent2024/Day08/test-input.txt")
        assertEquals(14, res)
    }

    @Test
    override fun puzzle1Input() {
        val day08 = Day08()
        val res = day08.puzzle1("advent2024/Day08/input.txt")
        assertEquals(291, res)
    }

    @Test
    override fun puzzle2Test() {
        val day08 = Day08()
        val res = day08.puzzle2("advent2024/Day08/test-input.txt")
        assertEquals(34, res)
    }

    @Test
    override fun puzzle2Input() {
        val day08 = Day08()
        val res = day08.puzzle2("advent2024/Day08/input.txt")
        assertEquals(1015, res)
    }
}