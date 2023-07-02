package printscript

import CommonParser
import errorHandler.ErrorHandler
import fromatter.Formatter
import implementation.Interpreter
import interfaces.ASTNode
import kotlinx.coroutines.flow.Flow
import lexer.lexer.Lexer
import linter.Linter
import java.io.File

class CommonPrintScriptRunner : PrintscriptRunner {
    private val lexer = Lexer()
    private val parser = CommonParser()
    private val interpreter = Interpreter.InterpreterConstructor.create()
    private val formatter = Formatter()
    private val linter = Linter()

    override fun runValidation(source: Flow<String>): Boolean {
        return try {
            val tokens = lexer.getTokens(source)
            val ast = parser.parseTokens(tokens)
            interpreter.interpret(ast)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun runExecution(
        source: Flow<String>,
        printFunction: (output: String) -> Unit,
        readFunction: (output: String) -> String,
        errorHandler: ErrorHandler
    ) {
        try {
            val tokens = lexer.getTokens(source)
            val ast = parser.parseTokens(tokens)
            interpreter.interpret(ast)
        } catch (e: Exception) {
            errorHandler.reportError(e.message)
        }
    }

    override fun runFormatting(source: Flow<String>, configFile: File): String {
        val ast = getNode(source)
        val formatted = formatter.getFormattedCode(ast, configFile)
        println(formatted)
        return formatted
    }

    private fun getNode(source: Flow<String>): ASTNode {
        val tokens = lexer.getTokens(source)
        return parser.parseTokens(tokens)
    }

    override fun runAnalyzing(source: Flow<String>, configFile: File): String {
        val ast = getNode(source)
        val linted = linter.getLintedCodeCorrection(ast, configFile)
        println(linted)
        return linted
    }
}
