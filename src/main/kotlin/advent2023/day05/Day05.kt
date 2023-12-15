package advent2023.day05

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


class Day05 {

    fun solvePart1Day05(filePath: String): Long {

        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())

        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var l: String?
                var lines: MutableList<String> = mutableListOf()
                var seedList: MutableList<Long> = mutableListOf()
                var seedToSoilMap: MutableList<String> = mutableListOf()
                var soilToFertilizerMap : MutableList<String> = mutableListOf()
                var fertilizerToWaterMap : MutableList<String> = mutableListOf()
                var waterToLightMap : MutableList<String> = mutableListOf()
                var lightToTemperatureMap : MutableList<String> = mutableListOf()
                var temperatureToHumidityMap : MutableList<String> = mutableListOf()
                var humidityToLocationMap : MutableList<String> = mutableListOf()
                while (bufferedReader.readLine().also { l = it } != null) {
                    lines.add(l!!)
                }
                var changeableMap : MutableList<String> = mutableListOf()
                for (line in lines) {
                    if (line.startsWith("seeds:")) {
                        var split = line.split(": ")[1].split(" ")
                        for (num in split) {
                            if (num.toLongOrNull() != null) {
                                seedList.add(num.toLong())
                            }
                        }
                    } else if (line.startsWith("seed-to-soil")) {
                        changeableMap = seedToSoilMap

                    } else if (line.startsWith("soil-to-fertilizer")) {
                        changeableMap = soilToFertilizerMap

                    } else if (line.startsWith("fertilizer-to-water")) {
                        changeableMap = fertilizerToWaterMap

                    }  else if (line.startsWith("water-to-light")) {
                        changeableMap = waterToLightMap

                    } else if (line.startsWith("light-to-temperature")) {
                        changeableMap = lightToTemperatureMap

                    } else if (line.startsWith("temperature-to-humidity")) {
                        changeableMap = temperatureToHumidityMap

                    } else if (line.startsWith("humidity-to-location")) {
                        changeableMap = humidityToLocationMap

                    } else if (line.isNotEmpty()) {
                       changeableMap.add(line)
                    }
                }
                var listOfMaps : MutableList<MutableList<String>> = mutableListOf(seedToSoilMap, soilToFertilizerMap, fertilizerToWaterMap, waterToLightMap, lightToTemperatureMap, temperatureToHumidityMap, humidityToLocationMap)
                return getResultFromMaps1(seedList, listOfMaps)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    private fun getResultFromMaps1(
        seedList: MutableList<Long>,
        listOfMaps: MutableList<MutableList<String>>
    ): Long {
        //0: seedToSoil
        //1: soilToFert
        //2: fertToWater
        //3: WaterToLight
        //4: LightToTemp
        //5: TempToHumi
        //6: HumiToLoc
        var firstList = listOfMaps.removeFirst()
        var locations : MutableList<Long> = mutableListOf()
        for (seed in seedList) {
            var soil = getDestFromSource1(seed, firstList)
            for (map in listOfMaps) {
                soil = getDestFromSource1(soil, map)
            }
            locations.add(soil)
        }
        return locations.min()
    }

    private fun getDestFromSource1(source: Long, destList: MutableList<String>): Long {
        for (seedToSoil in destList) {
            var split = seedToSoil.split(" ").filter { it.toLongOrNull() != null }
            if (split[1].toLong() <= source && source <= (split[1].toLong() + split[2].toLong())) {
                //TODO
                var diff = source - split[1].toLong()
                return split[0].toLong() + diff
            }
        }
        return source
    }


    fun solvePart2Day05(filePath: String): Long {
        val classLoader = this.javaClass.classLoader
        val resource = classLoader.getResource(filePath)
        val file = File(resource!!.toURI())
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                var l: String?
                var lines: MutableList<String> = mutableListOf()
                var seedList: MutableList<Long> = mutableListOf()
                var seedToSoilMap: MutableList<String> = mutableListOf()
                var soilToFertilizerMap : MutableList<String> = mutableListOf()
                var fertilizerToWaterMap : MutableList<String> = mutableListOf()
                var waterToLightMap : MutableList<String> = mutableListOf()
                var lightToTemperatureMap : MutableList<String> = mutableListOf()
                var temperatureToHumidityMap : MutableList<String> = mutableListOf()
                var humidityToLocationMap : MutableList<String> = mutableListOf()
                while (bufferedReader.readLine().also { l = it } != null) {
                    lines.add(l!!)
                }
                var changeableMap : MutableList<String> = mutableListOf()
                for (line in lines) {
                    if (line.startsWith("seeds:")) {
                        var split = line.split(": ")[1].split(" ")
                        for (num in split) {
                            if (num.toLongOrNull() != null) {
                                seedList.add(num.toLong())
                            }
                        }
                    } else if (line.startsWith("seed-to-soil")) {
                        changeableMap = seedToSoilMap

                    } else if (line.startsWith("soil-to-fertilizer")) {
                        changeableMap = soilToFertilizerMap

                    } else if (line.startsWith("fertilizer-to-water")) {
                        changeableMap = fertilizerToWaterMap

                    }  else if (line.startsWith("water-to-light")) {
                        changeableMap = waterToLightMap

                    } else if (line.startsWith("light-to-temperature")) {
                        changeableMap = lightToTemperatureMap

                    } else if (line.startsWith("temperature-to-humidity")) {
                        changeableMap = temperatureToHumidityMap

                    } else if (line.startsWith("humidity-to-location")) {
                        changeableMap = humidityToLocationMap

                    } else if (line.isNotEmpty()) {
                        changeableMap.add(line)
                    }
                }
                var listOfMaps : MutableList<MutableList<String>> = mutableListOf(seedToSoilMap, soilToFertilizerMap, fertilizerToWaterMap, waterToLightMap, lightToTemperatureMap, temperatureToHumidityMap, humidityToLocationMap)
                return getResultFromMaps2(seedList, listOfMaps)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    private fun getResultFromMaps2(
        seedList: MutableList<Long>,
        listOfMaps: MutableList<MutableList<String>>
    ): Long {
        //0: seedToSoil
        //1: soilToFert
        //2: fertToWater
        //3: WaterToLight
        //4: LightToTemp
        //5: TempToHumi
        //6: HumiToLoc
        var firstList = listOfMaps.removeFirst()
        var location = Long.MAX_VALUE
        for (i in 0 until seedList.size step 2) {
            var seedStart = seedList[i]
            var endRange = seedList[i+1]+seedStart
            while (seedStart != endRange) {
                var soil = getDestFromSource2(seedStart, firstList)
                for (map in listOfMaps) {
                    try {
                        soil = getDestFromSource2(soil, map)
                    } catch (e: OutOfMemoryError) {
                        e.printStackTrace()

                    }
                }
                if (soil < location) {
                    location = soil
                }
                seedStart += 1
            }

        }

        return location
    }
    private fun getDestFromSource2(source: Long, destList: MutableList<String>): Long {
        for (seedToSoil in destList) {
            var split = seedToSoil.split(" ").filter { it.toLongOrNull() != null }
            if (split[1].toLong() <= source && source <= (split[1].toLong() + split[2].toLong())) {
                var diff = source - split[1].toLong()
                return split[0].toLong() + diff
            }
        }
        return source
    }

}