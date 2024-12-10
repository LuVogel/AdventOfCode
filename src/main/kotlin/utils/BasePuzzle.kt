package utils

abstract class BasePuzzle {

    val helper = HelperUtils()

    abstract fun puzzle1(filePath: String): Any

    abstract fun puzzle2(filePath: String): Any

    fun getInput(filePath: String): MutableList<String> {
        return helper.readFilesAsStringList(filePath)
    }

    fun convertInputToMatrix(input: MutableList<String>): MutableList<MutableList<String>> {
        return helper.convertInputToMatrix(input)
    }
}