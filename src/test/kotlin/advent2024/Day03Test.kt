package advent2024

import utils.TestPuzzle
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day03 = Day03()
        val res = day03.puzzle1("advent2024/Day03/test-input.txt")
        assertEquals(161, res)
    }

    @Test
    override fun puzzle1Input() {
        val day03 = Day03()
        val res = day03.puzzle1("advent2024/Day03/input.txt")
        assertEquals(173785482, res)
    }

    @Test
    override fun puzzle2Test() {
        val day03 = Day03()
        val res = day03.puzzle2("advent2024/Day03/test-input2.txt")
        assertEquals(48, res)
    }

    @Test
    override fun puzzle2Input() {
        val day03 = Day03()
        val res = day03.puzzle2("advent2024/Day03/input.txt")
        assertEquals(83158140, res)
    }
}