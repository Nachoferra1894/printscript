package printscript

import CommonParser
import fromatter.Formatter
import implementation.Interpreter
import input.LexerFileInput
import lexer.lexer.Lexer
import version.V1
import version.Version
import java.io.File

class CommonPrintScriptRunner(private val version: Version) : PrintscriptRunner {
    private val lexer = Lexer()
    private val parser = CommonParser()
    private val interpreter = Interpreter.create(version)
    private val formatter = Formatter()

    override fun runValidation(sourceFile: File): String {
        return ""
    }

    override suspend fun runExecution(
        sourceFile: File,
        printFunction: (output: String) -> Unit
    ): String {
        val lexerFileInput = LexerFileInput(sourceFile)
        val tokens = lexer.getTokens(lexerFileInput.getFlow(), V1()) // TODO SETEAR VERSION
        val ast = parser.parseTokens(tokens, version)
        println(ast)
        val finalString = interpreter.interpret(ast)
        // TODO add interpreter
        return ast.toString()
    }

    override fun runFormatting(sourceFile: File, configFile: File): String {
        val lexerFileInput = LexerFileInput(sourceFile)
        val tokens = lexer.getTokens(lexerFileInput.getFlow(), version)
        val ast = parser.parseTokens(tokens, version)
        val formatted = formatter.getFormattedCode(ast, V1()) // TODO SETEAR VERSION
        println(formatted)
        return formatted
    }

    override fun runAnalyzing(sourceFile: File): String {
        return ""
    }
}
