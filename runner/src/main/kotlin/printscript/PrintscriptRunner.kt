package printscript

import java.io.File

interface PrintscriptRunner {
    fun runValidation(sourceFile: File): Boolean
    suspend fun runExecution(sourceFile: File, printFunction: (output: String) -> Unit, readFunction: (output: String) -> String): String
    fun runFormatting(sourceFile: File, configFile: File): String
    fun runAnalyzing(sourceFile: File, configFile: File): String
}
