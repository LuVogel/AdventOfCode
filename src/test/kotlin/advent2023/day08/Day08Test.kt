package advent2023.day08

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day08Test {

    @Test
    fun `day08 - part 1 - test input`() {
        val day08 = Day08()
        val result = day08.solvePart1Day08("advent2023/day08/day_08_part_1_test_input.txt")
        assertEquals(2, result)
    }
    @Test
    fun `day08 - part 1 - test2 input`() {
        val day08 = Day08()
        val result = day08.solvePart1Day08("advent2023/day08/day_08_part_1_test2_input.txt")
        assertEquals(6, result)
    }

    @Test
    fun `day08 - part 1 - puzzle input`() {
        val day08 = Day08()
        val result = day08.solvePart1Day08("advent2023/day08/day_08_puzzle_input.txt")
        assertEquals(22411, result)
    }

    @Test
    fun `day08 - part 2 -test input`() {
        val day08 = Day08()
        val result = day08.solvePart2Day08("advent2023/day08/day_08_part_2_test_input.txt")
        assertEquals(6, result)
    }

    @Test
    fun `day08 - part 2 - puzzle input`() {
        val day08 = Day08()
        val result = day08.solvePart2Day08("advent2023/day08/day_08_puzzle_input.txt")
        assertEquals(253630098, result)
    }
}