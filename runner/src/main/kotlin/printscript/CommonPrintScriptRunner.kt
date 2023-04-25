package printscript

import CommonParser
import fromatter.Formatter
import implementation.Interpreter
import input.LexerFileInput
import interfaces.ASTNode
import lexer.lexer.Lexer
import linter.Linter
import version.V1
import version.Version
import java.io.File

class CommonPrintScriptRunner(private val version: Version) : PrintscriptRunner {
    private val lexer = Lexer()
    private val parser = CommonParser()
    private val interpreter = Interpreter.create(version)
    private val formatter = Formatter()
    private val linter = Linter()

    override fun runValidation(sourceFile: File): Boolean {
        val lexerFileInput = LexerFileInput(sourceFile)
        val tokens = lexer.getTokens(lexerFileInput.getFlow(), V1())
        val ast = parser.parseTokens(tokens, version)
        println(ast)
        val finalString = interpreter.interpret(ast)
        // TODO add interpreter
        return true
    }

    override suspend fun runExecution(
        sourceFile: File,
        printFunction: (output: String) -> Unit,
        readFunction: (output: String) -> String
    ): String {
        val lexerFileInput = LexerFileInput(sourceFile)
        val tokens = lexer.getTokens(lexerFileInput.getFlow(), V1())
        val ast = parser.parseTokens(tokens, version)
        println(ast)
        val finalString = interpreter.interpret(ast)
        // TODO add interpreter
        return ast.toString()
    }

    override fun runFormatting(sourceFile: File, configFile: File): String {
        val ast = getNode(sourceFile)
        val formatted = formatter.getFormattedCode(ast, configFile)
        println(formatted)
        return formatted
    }

    private fun getNode(sourceFile: File): ASTNode {
        val lexerFileInput = LexerFileInput(sourceFile)
        val tokens = lexer.getTokens(lexerFileInput.getFlow(), version)
        return parser.parseTokens(tokens, version)
    }

    override fun runAnalyzing(sourceFile: File, configFile: File): String {
        val ast = getNode(sourceFile)
        return linter.getLintedCodeCorrection(ast, configFile, version)
    }
}
