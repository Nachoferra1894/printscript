package printscript

interface PrintscriptRunner {
    fun runValidation(sourceFile: String, version: String)
    suspend fun runExecution(sourceFile: String, version: String)
    fun runFormatting(sourceFile: String, version: String, arguments: String)
    fun runAnalyzing(sourceFile: String, version: String)
}
