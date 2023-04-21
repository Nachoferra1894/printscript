package lexer.lexer

import Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.withIndex
import languageDefinitions.Strategies
import lexer.generators.IndexGenerator.Companion.defineIndex
import lexer.interfaces.LexerI
import version.Version

class Lexer : LexerI {

    override fun getTokens(codeFlow: Flow<String>, version: Version): Flow<Token> = flow {
        codeFlow.withIndex().collect { (index, line) ->
            defineTokens(line, index, version).forEach { token ->
                emit(token)
            }
        }
    }

    fun defineTokens(line: String, lineIndex: Int, version: Version): ArrayList<Token> {
        val strategies = Strategies()
        val tokens: ArrayList<Token> = ArrayList()
        var index = 0
        while (index <= line.length - 1) {
            tokens.add(defineToken(line, index, strategies, lineIndex, version))
            index = defineIndex(tokens, index)
        }
        return tokens
    }

    private fun defineToken(line: String, index: Int, strategies: Strategies, lineIndex: Int, version: Version): Token {
        return strategies.defineTokens(line, index, lineIndex, version)
    }
}
