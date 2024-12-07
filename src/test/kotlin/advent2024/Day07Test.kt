package advent2024

import utils.TestPuzzle
import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test : TestPuzzle() {

    @Test
    override fun puzzle1Test() {
        val day07 = Day07()
        val res = day07.puzzle1("advent2024/Day07/test-input.txt")
        assertEquals(3749L, res)
    }

    @Test
    override fun puzzle1Input() {
        val day07 = Day07()
        val res = day07.puzzle1("advent2024/Day07/input1.txt")
        assertEquals(6231007345478L, res)
    }

    @Test
    override fun puzzle2Test() {
        val day07 = Day07()
        val res = day07.puzzle2("advent2024/Day07/test-input.txt")
        assertEquals(11387L, res)
    }

    @Test
    override fun puzzle2Input() {
        val day07 = Day07()
        val res = day07.puzzle2("advent2024/Day07/input1.txt")
        assertEquals(333027885676693L, res)
    }
}