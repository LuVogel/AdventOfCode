package advent2023.day05

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day05Test {

    @Test
    fun `day05 - part 1 - test input`() {
        val day05 = Day05()
        val result = day05.solvePart1Day05("advent2023/day05/day_05_part_1_test_input.txt")
        assertEquals(35, result)
    }

    @Test
    fun `day05 - part 1 - puzzle input`() {
        val day05 = Day05()
        val result = day05.solvePart1Day05("advent2023/day05/day_05_puzzle_input.txt")
        assertEquals(265018614, result)
    }

    @Test
    fun `day05 - part 2 -test input`() {
        val day05 = Day05()
        val result = day05.solvePart2Day05("advent2023/day05/day_05_part_1_test_input.txt")
        assertEquals(46, result)
    }

    @Test
    fun `day05 - part 2 - puzzle input`() {
        val day05 = Day05()
        val result = day05.solvePart2Day05("advent2023/day05/day_05_puzzle_input.txt")
        assertEquals(9881058, result)
    }
}