package printscript

import configuration.ConfigClasses
import configurationLinter.ConfigClassesLinter
import errorHandler.ErrorHandler
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PrintscriptRunner {
    fun runValidation(source: Flow<String>): Boolean
    fun runExecution(source: Flow<String>, errorHandler: ErrorHandler): HashMap<String,Any?>
    fun runFormatting(source: Flow<String>, configFile: File): String
    fun runFormatting(source: Flow<String>, configClasses: ArrayList<ConfigClasses>): String
    fun runAnalyzing(source: Flow<String>, configFile: File): String
    fun runAnalyzing(source: Flow<String>, configClasses: ArrayList<ConfigClassesLinter>): String
}
