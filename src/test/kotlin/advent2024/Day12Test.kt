package advent2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.TestPuzzle

class Day12Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day = Day12()
        val res = day.puzzle1("advent2024/Day12/test-input.txt")
        assertEquals(140, res)
    }

    @Test
    fun puzzle1Test1() {
        val day = Day12()
        val res = day.puzzle1("advent2024/Day12/test-input1.txt")
        assertEquals(772, res)
    }
    @Test
    fun puzzle1Test2() {
        val day = Day12()
        val res = day.puzzle1("advent2024/Day12/test-input2.txt")
        assertEquals(1930, res)
    }

    @Test
    override fun puzzle1Input() {
        val day = Day12()
        val res = day.puzzle1("advent2024/Day12/input.txt")
        assertEquals(1400386, res)
    }

    @Test
    override fun puzzle2Test() {
        val day = Day12()
        val res = day.puzzle2("advent2024/Day12/test-input.txt")
        assertEquals(80, res)
    }
    @Test
    fun puzzle2Test1() {
        val day = Day12()
        val res = day.puzzle2("advent2024/Day12/test-input1.txt")
        assertEquals(436, res)
    }

    @Test
    override fun puzzle2Input() {
        val day = Day12()
        val res = day.puzzle2("advent2024/Day12/input.txt")
        assertEquals(55312L, res)
    }
}