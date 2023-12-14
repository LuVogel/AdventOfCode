package advent2023.day02

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Test {

    @Test
    fun `day02 - part 1 - test input`() {
        val day02 = Day02()
        val result = day02.solvePart1Day02("advent2023/day02/day_02_part_1_test_input.txt")
        assertEquals(8, result)
    }

    @Test
    fun `day02 - part 1 - puzzle input`() {
        val day02 = Day02()
        val result = day02.solvePart1Day02("advent2023/day02/day_02_puzzle_input.txt")
        assertEquals(2683, result)
    }

    @Test
    fun `day02 - part 2 -test input`() {
        val day02 = Day02()
        val result = day02.solvePart2Day02("advent2023/day02/day_02_part_1_test_input.txt")
        assertEquals(2286, result)
    }

    @Test
    fun `day02 - part 2 - puzzle input`() {
        val day02 = Day02()
        val result = day02.solvePart2Day02("advent2023/day02/day_02_puzzle_input.txt")
        assertEquals(49710, result)
    }
}