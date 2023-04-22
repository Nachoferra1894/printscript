package printscript

import java.io.File

interface PrintscriptRunner {
    fun runValidation(sourceFile: File): String
    suspend fun runExecution(sourceFile: File, printFunction: (output: String) -> Unit): String
    fun runFormatting(sourceFile: File, configFile: File): String
    fun runAnalyzing(sourceFile: File): String
}
