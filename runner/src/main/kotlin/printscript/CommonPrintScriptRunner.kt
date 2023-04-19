package printscript

import V1Parser
import fromatter.Formatter
import implementation.Interpreter
import input.LexerFileInput
import lexer.lexer.Lexer
import java.io.File

class CommonPrintScriptRunner : PrintscriptRunner {
    private val lexer = Lexer()
    private val parser = V1Parser()
    private val interpreter = Interpreter.create()
    private val formatter = Formatter()

    override fun runValidation(sourceFile: File, version: String): String {
        return ""
    }

    override suspend fun runExecution(
        sourceFile: File,
        version: String,
        printFunction: (output: String) -> Unit
    ): String {
        val lexerFileInput = LexerFileInput(sourceFile)
        val tokens = lexer.getTokens(lexerFileInput.getFlow())
        val ast = parser.parseTokens(tokens)
        println(ast)
        val finalString = interpreter.interpret(ast)
        // TODO add interpreter
        return ast.toString()
    }

    override fun runFormatting(sourceFile: File, version: String, configFile: File): String {
        val lexerFileInput = LexerFileInput(sourceFile)
        val tokens = lexer.getTokens(lexerFileInput.getFlow())
        val ast = parser.parseTokens(tokens)
        val formatted = formatter.getFormattedCode(ast)
        println(formatted)
        return formatted
    }

    override fun runAnalyzing(sourceFile: File, version: String): String {
        return ""
    }
}
