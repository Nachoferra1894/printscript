package printscript

import V1Parser
import input.LexerFileInput
import lexer.lexer.Lexer
import java.io.File

class CommonPrintScriptRunner : PrintscriptRunner {
    private val lexer = Lexer()
    private val parser = V1Parser()

    override fun runValidation(sourceFile: File, version: String): String {
        return ""
    }

    override suspend fun runExecution(sourceFile: File, version: String): String {
        val lexerFileInput = LexerFileInput(sourceFile)
        val tokens = lexer.getTokens(lexerFileInput.getFlow())
        val ast = parser.parseTokens(tokens)
        // TODO add interpreter
        return ast.toString()
    }

    override fun runFormatting(sourceFile: File, version: String, arguments: String): String {
        return ""
    }

    override fun runAnalyzing(sourceFile: File, version: String): String {
        return ""
    }
}
