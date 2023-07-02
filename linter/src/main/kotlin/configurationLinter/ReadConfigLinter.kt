package configurationLinter

import com.google.gson.Gson
import java.io.File

class ReadConfigLinter {
    private val configClasses: ArrayList<ConfigClasses> = ArrayList()
    fun getJsonDataFromAsset(configFile: File): ArrayList<ConfigClasses>? {
        return try {
            val jsonString: String = configFile.bufferedReader().use { it.readText() }
            val gson = Gson()
            val config = gson.fromJson(jsonString, ConfigLinter::class.java)
            configClasses.add(defineCaseClass(config))
            configClasses.add(definePrintClass(config))
            configClasses.add(defineReadInputClass(config))
            configClasses
        } catch (ioException: Exception) {
            ioException.printStackTrace()
            null
        }
    }

    private fun definePrintClass(config: ConfigLinter): PrintCase {
        if (config.v1["printWithOperation"] == true) {
            return PrintOperations()
        }
        return PrintNormal()
    }

    private fun defineReadInputClass(config: ConfigLinter): ReadInputCase {
        if (config.v1["readInputWithOperation"] == true) {
            return ReadInputOperations()
        }
        return ReadInputNormal()
    }

    private fun defineCaseClass(config: ConfigLinter): TextCase {
        if (config.v1["camelCaseApproved"] == true) {
            return CamelCase()
        }
        return SnakeCase()
    }
    fun getConfigClasses(): ArrayList<ConfigClasses> {
        return configClasses
    }
}
