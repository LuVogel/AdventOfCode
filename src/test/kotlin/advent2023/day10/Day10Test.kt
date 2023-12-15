package advent2023.day10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {

    @Test
    fun `day10 - part 1 - test input`() {
        val day10 = Day10()
        val result = day10.solvePart1Day10("advent2023/day10/day_10_part_1_test_input.txt")
        assertEquals(4, result)
    }

    @Test
    fun `day10 - part 1 - test 2 input`() {
        val day10 = Day10()
        val result = day10.solvePart1Day10("advent2023/day10/day_10_part_1_test2_input.txt")
        assertEquals(8, result)
    }

    @Test
    fun `day10 - part 1 - puzzle input`() {
        val day10 = Day10()
        val result = day10.solvePart1Day10("advent2023/day10/day_10_puzzle_input.txt")
        assertEquals(1974232246, result)
    }

    @Test
    fun `day10 - part 2 - test input`() {
        val day10 = Day10()
        val result = day10.solvePart2Day10("advent2023/day10/day_10_part_1_test_input.txt")
        assertEquals(2, result)
    }

    @Test
    fun `day10 - part 2 - puzzle input`() {
        val day10 = Day10()
        val result = day10.solvePart2Day10("advent2023/day10/day_10_puzzle_input.txt")
        assertEquals(928, result)
    }
}