package advent2023.day03

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test {

    @Test
    fun `day03 - part 1 - test input`() {
        val day03 = Day03()
        val result = day03.solvePart1Day03("advent2023/day03/day_03_part_1_test_input.txt")
        assertEquals(4361, result)
    }

    @Test
    fun `day03 - part 1 - puzzle input`() {
        val day03 = Day03()
        val result = day03.solvePart1Day03("advent2023/day03/day_03_puzzle_input.txt")
        assertEquals(527144, result)
    }

    @Test
    fun `day03 - part 2 -test input`() {
        val day03 = Day03()
        val result = day03.solvePart2Day03("advent2023/day03/day_03_part_1_test_input.txt")
        assertEquals(467835, result)
    }

    @Test
    fun `day03 - part 2 - puzzle input`() {
        val day03 = Day03()
        val result = day03.solvePart2Day03("advent2023/day03/day_03_puzzle_input.txt")
        assertEquals(81463996, result)
    }
}