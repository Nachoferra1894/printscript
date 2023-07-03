package configuration

import com.google.gson.Gson
import java.io.File
import java.io.IOException

class ReadConfig {
    private val configClasses: ArrayList<ConfigClasses> = ArrayList()
    fun getJsonDataFromAsset(configFile: File): ArrayList<ConfigClasses>? {
        return try {
            if (!configFile.exists()) {
                return getDefaultArray()
            }
            val jsonString: String = configFile.bufferedReader().use { it.readText() }
            val gson = Gson()
            val config = gson.fromJson(jsonString, Config::class.java)
            defineSpaceColon(config)
            defineSpaceAssignation(config)
            defineLineBreakBeforePrintln(config)
            spaceIndexedForIf(config)
            configClasses
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }
    }

    private fun getDefaultArray(): ArrayList<ConfigClasses> {
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeAssignation())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(LineBrakeForPrintln(1))
        configClasses.add(SpaceIndexedForIf(1))
        return configClasses
    }
    private fun defineSpaceColon(config: Config) {
        if (config.v1["spaceBeforeColon"] == "true") {
            configClasses.add(SpaceBeforeColon())
        }
        if (config.v1["spaceAfterColon"] == "true") {
            configClasses.add(SpaceAfterColon())
        }
    }

    private fun defineSpaceAssignation(config: Config) {
        if (config.v1["spaceBeforeAssignation"] == "true") {
            configClasses.add(SpaceBeforeAssignation())
        }
        if (config.v1["spaceAfterAssignation"] == "true") {
            configClasses.add(SpaceAfterAssignation())
        }
    }

    private fun defineLineBreakBeforePrintln(config: Config) {
        if (config.v1["lineBreakBeforePrintln"] != null) {
            configClasses.add(LineBrakeForPrintln(config.v1["lineBreakBeforePrintln"]!!.toInt()))
        }
    }

    private fun spaceIndexedForIf(config: Config) {
        if (config.v1["spaceIndexedForIf"] != null) {
            configClasses.add(SpaceIndexedForIf(config.v1["spaceIndexedForIf"]!!.toInt()))
        }
    }
}
