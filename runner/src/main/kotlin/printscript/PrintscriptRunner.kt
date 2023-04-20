package printscript

import java.io.File

interface PrintscriptRunner {
    fun runValidation(sourceFile: File, version: String): String
    suspend fun runExecution(sourceFile: File, version: String, printFunction: (output: String) -> Unit): String
    fun runFormatting(sourceFile: File, version: String, configFile: File): String
    fun runAnalyzing(sourceFile: File, version: String): String
}
