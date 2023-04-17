package printscript

import V1Parser
import input.LexerFileInput
import lexer.lexer.Lexer
import java.io.File

class CommonPrintScriptRunner : PrintscriptRunner {

    override fun runValidation(sourceFile: String, version: String) {
    }

    override suspend fun runExecution(sourceFile: String, version: String) {
        val file = File(sourceFile)
        val lexer = Lexer()
        val parser = V1Parser()
        val lexerFileInput = LexerFileInput(file)
        val tokens = lexer.getTokens(lexerFileInput.getFlow())
        val ast = parser.parseTokens(tokens)
        println(ast)
    }

    override fun runFormatting(sourceFile: String, version: String, arguments: String) {
    }

    override fun runAnalyzing(sourceFile: String, version: String) {
    }
}
