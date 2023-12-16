package advent2023.day11

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun `day11 - part 1 - test input`() {
        val day11 = Day11()
        val result = day11.solveDay11("advent2023/day11/day_11_part_1_test_input.txt", 1)
        assertEquals(374, result)
    }



    @Test
    fun `day11 - part 1 - puzzle input`() {
        val day11 = Day11()
        val result = day11.solveDay11("advent2023/day11/day_11_puzzle_input.txt", 1)
        assertEquals(9563821, result)
    }

    @Test
    @Disabled
    fun `day11 - part 2 - test input - 10 times`() {
        val day11 = Day11()
        val result = day11.solveDay11("advent2023/day11/day_11_part_1_test_input.txt", 10)
        assertEquals(1030, result)
    }
    @Test
    @Disabled
    fun `day11 - part 2 - test 2 input - 100 times`() {
        val day11 = Day11()
        val result = day11.solveDay11("advent2023/day11/day_11_part_1_test_input.txt", 100)
        assertEquals(8410, result)
    }

    @Test
    @Disabled
    fun `day11 - part 2 - test 2 input - 1000000 times`() {
        val day11 = Day11()
        val result = day11.solveDay11("advent2023/day11/day_11_part_1_test_input.txt", 1000000)
        assertEquals(8410, result)
    }


    @Test
    @Disabled
    fun `day11 - part 2 - puzzle input`() {
        val day11 = Day11()
        val result = day11.solvePart2Day11("advent2023/day11/day_11_puzzle_input.txt")
        assertEquals(928, result)
    }
}