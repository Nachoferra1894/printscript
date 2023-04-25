package configuration

import com.google.gson.Gson
import java.io.File
import java.io.IOException

class ReadConfig {
    private val configClasses: ArrayList<ConfigClasses> = ArrayList()
    fun getJsonDataFromAsset(configFile: File): ArrayList<ConfigClasses>? {
        return try {
            val jsonString: String = configFile.bufferedReader().use { it.readText() }
            val gson = Gson()
            val config = gson.fromJson(jsonString, Config::class.java)
            defineConfigClass(config)
            configClasses
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }
    }

    // TODO: acomodar esto
    private fun defineConfigClass(config: Config) {
        val map = config.v1
        map.forEach {
                key, value ->
            if (key == "spaceBeforeColon" && value == "true") {
                configClasses.add(SpaceBeforeColon())
            }
            if (key == "spaceAfterColon" && value == "true") {
                configClasses.add(SpaceAfterColon())
            }
            if (key == "spaceBeforeAssignation" && value == "true") {
                configClasses.add(SpaceBeforeAssignation())
            }
            if (key == "spaceAfterAssignation" && value == "true") {
                configClasses.add(SpaceBeforeAssignation())
            }
            if (key == "spaceAfterAssignation" && value == "true") {
                configClasses.add(SpaceAfterAssignation())
            }
            if (key == "lineBreakBeforePrintln") {
                configClasses.add(LineBrakeForPrintln(value.toInt()))
            }
        }
    }

    fun getConfigClasses(): ArrayList<ConfigClasses> {
        return configClasses
    }
}
