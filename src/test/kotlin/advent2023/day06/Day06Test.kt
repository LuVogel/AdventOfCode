package advent2023.day06

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day06Test {

    @Test
    fun `day06 - part 1 - test input`() {
        val day06 = Day06()
        val result = day06.solvePart1Day06("advent2023/day06/day_06_part_1_test_input.txt")
        assertEquals(288, result)
    }

    @Test
    fun `day06 - part 1 - puzzle input`() {
        val day06 = Day06()
        val result = day06.solvePart1Day06("advent2023/day06/day_06_puzzle_input.txt")
        assertEquals(4403592, result)
    }

    @Test
    fun `day06 - part 2 -test input`() {
        val day06 = Day06()
        val result = day06.solvePart2Day06("advent2023/day06/day_06_part_1_test_input.txt")
        assertEquals(71503, result)
    }

    @Test
    fun `day06 - part 2 - puzzle input`() {
        val day06 = Day06()
        val result = day06.solvePart2Day06("advent2023/day06/day_06_puzzle_input.txt")
        assertEquals(38017587, result)
    }
}