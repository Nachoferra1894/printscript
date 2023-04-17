package lexer.lexer

import Token
import languageDefinitions.Strategies
import lexer.generators.IndexGenerator.Companion.defineIndex
import lexer.interfaces.LexerI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow

class Lexer : LexerI {

    override fun getTokens(codeFlow: Flow<String>): Flow<Token> = flow {
        codeFlow.flatMapConcat { line ->
            defineTokens(line).asFlow()
        }.collect { token ->
            emit(token)
        }
    }

    fun defineTokens(line: String): ArrayList<Token> {
        val strategies = Strategies()
        val tokens: ArrayList<Token> = ArrayList()
        var index = 0
        while (index <= line.length - 1) {
            tokens.add(defineToken(line, index, strategies))
            index = defineIndex(tokens, index)
        }
        return tokens
    }

    private fun defineToken(line: String, index: Int, strategies: Strategies): Token {
        return strategies.defineTokens(line, index)
    }
}
