package printscript

import java.io.File

interface PrintscriptRunner {
    fun runValidation(sourceFile: File, version: String): String
    suspend fun runExecution(sourceFile: File, version: String): String
    fun runFormatting(sourceFile: File, version: String, arguments: String): String
    fun runAnalyzing(sourceFile: File, version: String): String
}
