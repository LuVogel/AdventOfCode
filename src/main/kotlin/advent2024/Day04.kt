package advent2024

import utils.BasePuzzle

class Day04 : BasePuzzle() {


    override fun puzzle1(filePath: String): Int {
        val input  = getInput(filePath)
        val matrixList = mutableListOf<MutableList<String>>()
        val verticalList = mutableListOf<String>()
        for (line in input) {
            verticalList.add(line)
            matrixList.add(line.toList().map { it.toString() }.toMutableList())
        }
        val maxLength = input.maxOfOrNull { it.length } ?: 0
        val horizontalList = (0 until maxLength).map { index ->
            input.mapNotNull {
                it.getOrNull(index)
            }.joinToString("")
        }
        val diagonals = getDiagonals(matrixList)
        val regex = Regex("XMAS")
        return findMatchesInLists(listOf(verticalList, horizontalList, diagonals), regex, true)

    }

    private fun findMatchesInLists(allLists: List<List<String>>, regex: Regex, reversed: Boolean): Int {
        var sum = 0
        for (list in allLists) {
            for (s in list) {
                var matches = regex.findAll(s)
                if (matches.count() > 0) {
                    sum += matches.count()
                }
                if (reversed) {
                    matches = regex.findAll(s.reversed())
                    if (matches.count() > 0) {
                        sum += matches.count()
                    }
                }
            }
        }
        return sum
    }

    private fun getDiagonals(matrixList: MutableList<MutableList<String>>): MutableList<String> {
        val minLength = 4
        val rows = matrixList.size
        val cols = matrixList[0].size
        val diagonals = mutableListOf<String>()
        //Hauptdiagonalen
        for (d in -(rows - 1) until cols) {
            var diagonal = ""
            for (i in 0 until rows) {
                val j = i+d
                if (j in 0 until cols) {
                    diagonal += matrixList[i][j]
                }
            }
            if (diagonal.length >= minLength) {
                diagonals.add(diagonal)
            }
        }
        //Nebendiagonalen
        for (d in 0 until cols + rows -1) {
            var diagonal = ""
            for (i in 0 until rows) {
                val j = d-i
                if (j in 0 until cols) {
                    diagonal += matrixList[i][j]
                }
            }
            if (diagonal.length >= minLength) {
                diagonals.add(diagonal)
            }
        }

        return diagonals
    }

    override fun puzzle2(filePath: String): Any {
        val input = getInput(filePath)
        val matrixList = mutableListOf<MutableList<String>>()
        for (line in input) {
            matrixList.add(line.toList().map { it.toString() }.toMutableList())
        }
        val subMatrix = createSubMatrices(matrixList, 3)
        var sum = 0
        val regex = Regex("MAS")
        for (matrix in subMatrix) {
            val dias = createMainDiagonals(matrix)
            var diaCount = 0
            for (dia in dias) {
                var matches = regex.findAll(dia)
                if (matches.count() > 0) {
                    diaCount += matches.count()
                }
                matches = regex.findAll(dia.reversed())
                if (matches.count() > 0) {
                    diaCount += matches.count()
                }
            }
            if (diaCount >= 2) {
                sum++
            }
        }
        return sum

    }

    private fun createMainDiagonals(matrix: MutableList<MutableList<String>>): MutableList<String> {
        val minLength = 3
        val rows = matrix.size
        val cols = matrix[0].size
        val diagonals = mutableListOf<String>()

        // Diagonalen, die in der ersten Zeile beginnen
        for (colStart in 0 until cols) {
            val diagonal = StringBuilder()
            var i = 0
            var j = colStart
            while (i < rows && j < cols) {
                diagonal.append(matrix[i][j])
                i++
                j++
            }
            if (diagonal.length >= minLength) {
                diagonals.add(diagonal.toString())
            }
        }

        // Diagonalen, die in der ersten Spalte beginnen
        for (rowStart in 1 until rows) {
            val diagonal = StringBuilder()
            var i = rowStart
            var j = 0
            while (i < rows && j < cols) {
                diagonal.append(matrix[i][j])
                i++
                j++
            }
            if (diagonal.length >= minLength) {
                diagonals.add(diagonal.toString())
            }
        }

        // Zusätzliche Diagonalen von rechts nach links (Nebenhauptdiagonalen)
        for (colStart in 0 until cols) {
            val diagonal = StringBuilder()
            var i = 0
            var j = colStart
            while (i < rows && j >= 0) {
                diagonal.append(matrix[i][j])
                i++
                j--
            }
            if (diagonal.length >= minLength) {
                diagonals.add(diagonal.toString())
            }
        }

        for (rowStart in 1 until rows) {
            val diagonal = StringBuilder()
            var i = rowStart
            var j = cols - 1
            while (i < rows && j >= 0) {
                diagonal.append(matrix[i][j])
                i++
                j--
            }
            if (diagonal.length >= minLength) {
                diagonals.add(diagonal.toString())
            }
        }

        return diagonals
    }




    private fun createSubMatrices(matrixList: MutableList<MutableList<String>>, subMatrixSize: Int): MutableList<MutableList<MutableList<String>>> {
        val subMatrices = mutableListOf<MutableList<MutableList<String>>>()
        val rows = matrixList.size
        val cols = matrixList[0].size
        for (i in 0 .. rows - subMatrixSize) {
            for (j in 0 .. cols - subMatrixSize) {
                val subMatrix = mutableListOf<MutableList<String>>()
                for (x in i until i + subMatrixSize) {
                    val row = matrixList[x].subList(j, j + subMatrixSize).toMutableList()
                    subMatrix.add(row)
                }
                subMatrices.add(subMatrix)
            }
        }
        return subMatrices
    }
}