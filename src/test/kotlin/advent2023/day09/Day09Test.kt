package advent2023.day09

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day09Test {

    @Test
    fun `day09 - part 1 - test input`() {
        val day09 = Day09()
        val result = day09.solvePart1Day09("advent2023/day09/day_09_part_1_test_input.txt")
        assertEquals(114, result)
    }

    @Test
    fun `day09 - part 1 - puzzle input`() {
        val day09 = Day09()
        val result = day09.solvePart1Day09("advent2023/day09/day_09_puzzle_input.txt")
        assertEquals(1974232246, result)
    }

    @Test
    fun `day09 - part 2 - test input`() {
        val day09 = Day09()
        val result = day09.solvePart2Day09("advent2023/day09/day_09_part_1_test_input.txt")
        assertEquals(2, result)
    }

    @Test
    fun `day09 - part 2 - puzzle input`() {
        val day09 = Day09()
        val result = day09.solvePart2Day09("advent2023/day09/day_09_puzzle_input.txt")
        assertEquals(928, result)
    }
}