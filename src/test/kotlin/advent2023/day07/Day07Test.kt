package advent2023.day07

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day07Test {

    @Test
    fun `day07 - part 1 - test input`() {
        val day07 = Day07()
        val result = day07.solvePart1Day07("advent2023/day07/day_07_part_1_test_input.txt")
        assertEquals(6440, result)
    }

    @Test
    fun `day07 - part 1 - puzzle input`() {
        val day07 = Day07()
        val result = day07.solvePart1Day07("advent2023/day07/day_07_puzzle_input.txt")
        assertEquals(4403592, result)
    }

    @Test
    fun `day07 - part 2 -test input`() {
        val day07 = Day07()
        val result = day07.solvePart2Day07("advent2023/day07/day_07_part_1_test_input.txt")
        assertEquals(71503, result)
    }

    @Test
    fun `day07 - part 2 - puzzle input`() {
        val day07 = Day07()
        val result = day07.solvePart2Day07("advent2023/day07/day_07_puzzle_input.txt")
        assertEquals(38017587, result)
    }
}