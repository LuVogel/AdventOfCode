package utils

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class HelperUtils {



    public fun readFilesAsStringList(filePath: String): MutableList<String> {
        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        val list = mutableListOf<String>()
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var line: String?
                var sum = 0
                while (bufferedReader.readLine().also { line = it } != null) {
                    line?.let { list.add(it) }
                }
                return list

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return list
    }
}