package printscript

import input.LexerFileInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import lexer.lexer.Lexer
import java.io.File

class CommonPrintScriptRunner: PrintscriptRunner {

    override fun runValidation(sourceFile: String, version: String) {
    }

    override suspend fun runExecution(sourceFile: String, version: String) {
        val file = File(sourceFile);
        val lexer = Lexer()
        val lexerFileInput = LexerFileInput(file)
        lexer.getTokens(lexerFileInput.getFlow()).collect { token ->
            println(token)
        }
    }

    override fun runFormatting(sourceFile: String, version: String, arguments: String) {
    }

    override fun runAnalyzing(sourceFile: String, version: String) {
    }
}
