package advent2024

import utils.BasePuzzle

class Day07 : BasePuzzle() {

    data class Equation(val result: Long, val values: List<Long>)


    override fun puzzle1(filePath: String): Any {
        val input = getInput(filePath)
        val operators = listOf("+", "*")
        val equationList = getEquations(input)
        return calculateFound(equationList, operators)
    }

    override fun puzzle2(filePath: String): Any {
        val input = getInput(filePath)
        val equationList = getEquations(input)
        val operators = listOf("+", "*", "||")

        return calculateFound(equationList, operators)
    }

    private fun calculateFound(equationList: MutableList<Equation>, operators: List<String>): Long {
        var found = 0L
        for (equation in equationList) {
            val possibilities = Math.pow(operators.size.toDouble(), equation.values.size-1.0).toLong()
            for (possibility in 0 until possibilities) {
                var possibilityIndex = possibility
                var expression = mutableListOf<String>()
                var result = 0L
                for (index in equation.values.indices) {
                    expression.add(equation.values[index].toString())
                    if (index == 0) {
                        result = equation.values[index]
                    }  else if (expression[expression.size-2].equals("+")) {
                        result += equation.values[index]
                    } else if (expression[expression.size-2].equals("*")) {
                        result *= equation.values[index]
                    } else if (expression[expression.size-2].equals("||")) {
                        result = "$result${equation.values[index]}".toLong()
                    }
                    //choose operator based on modulo operation
                    if (result < equation.result || index < equation.values.size - 1) {
                        val operatorIndex = possibilityIndex % operators.size
                        expression.add(operators[operatorIndex.toInt()])
                        possibilityIndex /= operators.size
                    }
                }
                if (result == equation.result) {
                    found += result
                    break
                }
            }
        }
        return found
    }

    private fun getEquations(input: MutableList<String>) : MutableList<Equation> {
        val list = mutableListOf<Equation>()
        for (line in input) {
            val result = line.substringBefore(":").toLong()
            val values = line.substringAfter(":").split(" ").mapNotNull { it.toLongOrNull() }
            list.add(Equation(result, values))
        }
        return list
    }
}