package printscript

import CommonParser
import errorHandler.ErrorHandler
import fromatter.Formatter
import implementation.Interpreter
import interfaces.ASTNode
import kotlinx.coroutines.flow.Flow
import lexer.lexer.Lexer
import linter.Linter
import version.V1
import version.Version
import version.getLatestVersion
import java.io.File

class CommonPrintScriptRunner(private val version: Version = getLatestVersion()) : PrintscriptRunner {
    private val lexer = Lexer()
    private val parser = CommonParser()
    private val interpreter = Interpreter.InterpreterConstructor.create(version)
    private val formatter = Formatter()
    private val linter = Linter()

    override fun runValidation(source: Flow<String>): Boolean {
        return try {
            val tokens = lexer.getTokens(source, version)
            val ast = parser.parseTokens(tokens, version)
            interpreter.interpret(ast)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun runExecution(
        source: Flow<String>,
        printFunction: (output: String) -> Unit,
        readFunction: (output: String) -> String,
        errorHandler: ErrorHandler
    ) {
        try {
            val tokens = lexer.getTokens(source, V1())
            val ast = parser.parseTokens(tokens, version)
            interpreter.interpret(ast)
        } catch (e: Exception) {
            errorHandler.reportError(e.message)
        }
    }

    override fun runFormatting(source: Flow<String>, configFile: File): String {
        val ast = getNode(source)
        val formatted = formatter.getFormattedCode(ast, configFile, version)
        println(formatted)
        return formatted
    }

    private fun getNode(source: Flow<String>): ASTNode {
        val tokens = lexer.getTokens(source, version)
        return parser.parseTokens(tokens, version)
    }

    override fun runAnalyzing(source: Flow<String>, configFile: File): String {
        val ast = getNode(source)
        return linter.getLintedCodeCorrection(ast, configFile, version)
    }
}
