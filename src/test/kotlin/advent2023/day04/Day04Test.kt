package advent2023.day04

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Test {

    @Test
    fun `day04 - part 1 - test input`() {
        val day04 = Day04()
        val result = day04.solvePart1Day04("advent2023/day04/day_04_part_1_test_input.txt")
        assertEquals(13, result)
    }

    @Test
    fun `day04 - part 1 - puzzle input`() {
        val day04 = Day04()
        val result = day04.solvePart1Day04("advent2023/day04/day_04_puzzle_input.txt")
        assertEquals(21919, result)
    }

    @Test
    fun `day04 - part 2 -test input`() {
        val day04 = Day04()
        val result = day04.solvePart2Day04("advent2023/day04/day_04_part_1_test_input.txt")
        assertEquals(30, result)
    }

    @Test
    fun `day04 - part 2 - puzzle input`() {
        val day04 = Day04()
        val result = day04.solvePart2Day04("advent2023/day04/day_04_puzzle_input.txt")
        assertEquals(9881048, result)
    }
}