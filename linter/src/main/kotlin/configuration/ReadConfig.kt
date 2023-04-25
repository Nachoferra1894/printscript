package configuration

import com.google.gson.Gson
import java.io.File
import java.io.IOException

class ReadConfig {
    private val configClasses: ArrayList<ConfigClasses> = ArrayList()
    fun getJsonDataFromAsset(): ArrayList<ConfigClasses>? {
        return try {
            val jsonString: String
            val file = File("./src/main/kotlin/configuration/config.json")
            jsonString = file.bufferedReader().use { it.readText() }
            val gson = Gson()
            val config = gson.fromJson(jsonString, Config::class.java)
            configClasses.add(defineCaseClass(config))
            configClasses.add(definePrintClass(config))
            configClasses.add(defineReadInputClass(config))
            configClasses
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }
    }

    fun definePrintClass(config: Config): PrintCase {
        if (config.v1["printWithOperation"] == true) {
            return PrintOperations()
        }
        return PrintNormal()
    }

    fun defineReadInputClass(config: Config): ReadInputCase {
        if (config.v1["readInputWithOperation"] == true) {
            return ReadInputOperations()
        }
        return ReadInputNormal()
    }

    fun defineCaseClass(config: Config): TextCase {
        if (config.v1["camelCaseApproved"] == true) {
            return CamelCase()
        }
        return SnakeCase()
    }
    fun getConfigClasses(): ArrayList<ConfigClasses> {
        return configClasses
    }
}
