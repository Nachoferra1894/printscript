package printscript

import kotlinx.coroutines.flow.Flow
import java.io.File

interface PrintscriptRunner {
    fun runValidation(source: Flow<String>): Boolean
    suspend fun runExecution(source: Flow<String>, printFunction: (output: String) -> Unit, readFunction: (output: String) -> String): String
    fun runFormatting(source: Flow<String>, configFile: File): String
    fun runAnalyzing(source: Flow<String>, configFile: File): String
}
