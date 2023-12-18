package advent2023.day12

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {

    @Test
    fun `day12 - part 1 - test input`() {
        val day12 = Day12()
        val result = day12.solvePart1Day12("advent2023/day12/day_12_part_1_test_input.txt")
        assertEquals(21, result)
    }



    @Test
    fun `day12 - part 1 - puzzle input`() {
        val day12 = Day12()
        val result = day12.solvePart1Day12("advent2023/day12/day_12_puzzle_input.txt")
        assertEquals(7490, result)
    }




    @Test
    @Disabled
    fun `day12 - part 2 - test input`() {
        val day12 = Day12()
        val result = day12.solvePart2Day12("advent2023/day12/day_12_part_1_test_input.txt")
        assertEquals(525152, result)
    }


    @Test
    @Disabled
    fun `day12 - part 2 - puzzle input`() {
        val day12 = Day12()
        val result = day12.solvePart2Day12("advent2023/day12/day_12_puzzle_input.txt")
        assertEquals(928, result)
    }
}