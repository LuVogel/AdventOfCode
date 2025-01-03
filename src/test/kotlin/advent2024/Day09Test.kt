package advent2024

import org.junit.jupiter.api.Assertions.assertEquals
import utils.TestPuzzle
import kotlin.test.Test

class Day09Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day09 = Day09()
        val res = day09.puzzle1("advent2024/Day09/test-input.txt")
        assertEquals(1928L, res)
    }

    @Test
    override fun puzzle1Input() {
        val day09 = Day09()
        val res = day09.puzzle1("advent2024/Day09/input.txt")
        assertEquals(6241633730082L, res)
    }

    @Test
    override fun puzzle2Test() {
        val day09 = Day09()
        val res = day09.puzzle2("advent2024/Day09/test-input.txt")
        assertEquals(2858L, res)
    }

    @Test
    override fun puzzle2Input() {
        val day09 = Day09()
        val res = day09.puzzle2("advent2024/Day09/input.txt")
        assertEquals(6265268809555L, res)
    }
}