package advent2024

import org.jetbrains.kotlinx.multik.api.linalg.LinAlg
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.api.linalg.solve
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.D2
import org.jetbrains.kotlinx.multik.ndarray.data.MultiArray
import org.jetbrains.kotlinx.multik.ndarray.operations.all
import org.jetbrains.kotlinx.multik.ndarray.operations.minus
import utils.BasePuzzle
import java.math.BigDecimal
import java.util.PriorityQueue
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.math.sin

class Day13 : BasePuzzle() {


    data class Button(var vector: Vector = Vector(0.0,0.0), var cost: Int = 0)
    data class Vector(var x: Double = 0.0, var y: Double = 0.0)

    public class SearchNode(
        val state: Vector,
        val parent: SearchNode?,
        val action: Button,
        val pathCost: Int,
        val heuristicCost: Int
    ) : Comparable<SearchNode> {
        val totalCost: Int get() = pathCost + heuristicCost
        override fun compareTo(other: SearchNode): Int {
            return this.totalCost- other.totalCost
        }
    }

    override fun puzzle1(filePath: String): Any {
       return solvePuzzle(filePath, 0.0)
    }

    override fun puzzle2(filePath: String): Any {
        return solvePuzzle(filePath, 10000000000000.0)
    }

    fun solvePuzzle(filePath: String, addToGoal: Double): Long {
        val input = getInput(filePath)
        val buttonA = Button(cost=3)
        var buttonB = Button(cost=1)
        var goal = Vector()
        var res = 0L
        for (line in input) {
            if (line.contains("Button A")) {
                buttonA.vector.x = line.substringAfter("X+").split(",")[0].toDouble()
                buttonA.vector.y = line.substringAfter("Y+").toDouble()
            } else if (line.contains("Button B")) {
                buttonB.vector.x = line.substringAfter("X+").split(",")[0].toDouble()
                buttonB.vector.y = line.substringAfter("Y+").toDouble()
            } else if (line.contains("Prize")) {
                goal.x = line.substringAfter("X=").split(",")[0].toDouble() + addToGoal
                goal.y = (line.substringAfter("Y=").toDouble()) + addToGoal
                res += getMinimumNumberOfTokens(buttonA, buttonB, goal, addToGoal)
            }
        }
        return res
    }

    fun getMinimumNumberOfTokens(buttonA: Button, buttonB: Button, goal: Vector, multiplyBy: Double): Long {
        val koeffA : MultiArray<Double, D2> = mk.ndarray(mk[
                mk[buttonA.vector.x, buttonB.vector.x],
                mk[buttonA.vector.y, buttonB.vector.y]
        ])

        val konstantB = mk.ndarray(mk[
            mk[goal.x],
            mk[goal.y]
        ])
        val solution = mk.linalg.solve(koeffA, konstantB)
        solution.data[0] = solution.data[0].roundToLong().toDouble()
        solution.data[1] = solution.data[1].roundToLong().toDouble()
        val product = koeffA dot solution
        if ((product - konstantB).all { it == 0.0 }) {
                return (3 * solution.data[0] + solution.data[1]).toLong().also { println("used tokens: $it") }
        }
//        val tolerance = 1e-9
//        val solutionA = solution.data[0]
//        val solutionB = solution.data[1]
//        println("found solution tokenA=$solutionA, tokenB=$solutionB for A(${buttonA.vector.x},${buttonA.vector.y}), B(${buttonB.vector.x},${buttonB.vector.y}) and goal: (${goal.x}, ${goal.y})")
//        if (multiplyBy != 1.0) {
//            if (abs(solutionA - solutionA.roundToLong()) < tolerance && abs(solutionB - solutionB.roundToLong()) < tolerance) {
//                val aInt = solutionA.roundToLong()
//                val bInt = solutionB.roundToLong()
//                return ((aInt * buttonA.cost.toLong()) + (buttonB.cost * bInt.toLong())).also { println("used tokens: $it") }
//
//            }
//        } else {
//            if (checkBigValue(solutionA) && checkBigValue(solutionB)) {
//                val aInt = solutionA.roundToInt()
//                val bInt = solutionB.roundToInt()
//                return ((aInt * buttonA.cost) + (buttonB.cost * bInt)).also { println("used tokens: $it") }
//            }
//        }


        return 0
    }

    fun checkBigValue(value: Double): Boolean {
        val factor = 1_000_000_000_000_000.0
        val shifted = factor * value
        val rounded = shifted.roundToLong()
        val backShifted = rounded / factor
        return backShifted == value
    }
}