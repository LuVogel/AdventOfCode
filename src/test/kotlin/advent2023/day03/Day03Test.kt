package advent2023.day03

import advent2023.Day03.Day03
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test {

    @Test
    fun `day03 - part 1 - test input`() {
        val day03 = Day03()
        val result = day03.solvePart1Day03("advent2023/day03/day_03_part_1_test_input.txt")
        assertEquals(8, result)
    }

    @Test
    fun `day03 - part 1 - puzzle input`() {
        val day03 = Day03()
        val result = day03.solvePart1Day03("advent2023/day03/day_03_puzzle_input.txt")
        assertEquals(3683, result)
    }

    @Test
    fun `day03 - part 3 -test input`() {
        val day03 = Day03()
        val result = day03.solvePart2Day03("advent2023/day03/day_03_part_1_test_input.txt")
        assertEquals(3386, result)
    }

    @Test
    fun `day03 - part 3 - puzzle input`() {
        val day03 = Day03()
        val result = day03.solvePart2Day03("advent2023/day03/day_03_puzzle_input.txt")
        assertEquals(49710, result)
    }
}