package advent2023.day01

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {

    @Test
    fun `day01 - part 1 - test input`() {
        val day01 = Day01()
        val result = day01.solvePart1Day01("advent2023/day01/day_01_part_1_test_input.txt")
        assertEquals(142, result)
    }

    @Test
    fun `day01 - part 1 - puzzle input`() {
        val day01 = Day01()
        val result = day01.solvePart1Day01("advent2023/day01/day_01_puzzle_input.txt")
        assertEquals(54940, result)
    }

    @Test
    fun `day01 - part 2 -test input`() {
        val day01 = Day01()
        val result = day01.solvePart2Day01("advent2023/day01/day_01_part_2_test_input.txt")
        assertEquals(281, result)
    }

    @Test
    fun `day01 - part 2 - puzzle input`() {
        val day01 = Day01()
        val result = day01.solvePart2Day01("advent2023/day01/day_01_puzzle_input.txt")
        assertEquals(54208, result)
    }
}