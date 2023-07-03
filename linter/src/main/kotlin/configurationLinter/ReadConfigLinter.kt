package configurationLinter

import com.google.gson.Gson
import java.io.File

class ReadConfigLinter {
    private val configClasses: ArrayList<ConfigClassesLinter> = ArrayList()
    fun getJsonDataFromAsset(configFile: File?): ArrayList<ConfigClassesLinter>? {
        return try {
            if (configFile == null || !configFile.exists()) {
                return getDefaultArray()
            }
            val jsonString: String = configFile.bufferedReader().use { it.readText() }
            val gson = Gson()
            val config = gson.fromJson(jsonString, ConfigLinter::class.java)
            configClasses.add(defineCaseClass(config))
            configClasses.addAll(definePrintClass(config))
            configClasses.addAll(defineReadInputClass(config))
            configClasses
        } catch (ioException: Exception) {
            ioException.printStackTrace()
            null
        }
    }

    private fun getDefaultArray(): ArrayList<ConfigClassesLinter> {
        configClasses.add(PrintOperations())
        configClasses.add(ReadInputOperations())
        configClasses.add(CamelCase())
        return configClasses
    }
    private fun definePrintClass(config: ConfigLinter): ArrayList<ConfigClassesLinter> {
        val array = ArrayList<ConfigClassesLinter>()
        if (config.v1["printWithOperation"] == true) {
            array.add(PrintOperations())
            array.add(PrintNormal())
        } else {
            array.add(PrintNormal())
        }
        return array
    }

    private fun defineReadInputClass(config: ConfigLinter): ArrayList<ConfigClassesLinter> {
        val array = ArrayList<ConfigClassesLinter>()
        if (config.v1["readInputWithOperation"] == true) {
            array.add(ReadInputOperations())
            array.add(ReadInputNormal())
        } else {
            array.add(ReadInputNormal())
        }
        return array
    }

    private fun defineCaseClass(config: ConfigLinter): TextCase {
        if (config.v1["camelCaseApproved"] == true) {
            return CamelCase()
        }
        return SnakeCase()
    }
}
