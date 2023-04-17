package lexer.lexer

import Token
import generators.TokenGenerator.Companion.getConstToken
import generators.TokenGenerator.Companion.getFinalToken
import generators.TokenGenerator.Companion.getIdentifierToken
import generators.TokenGenerator.Companion.getLetToken
import generators.TokenGenerator.Companion.getMethodPrintToken
import generators.TokenGenerator.Companion.getOperationStrategy
import generators.TokenGenerator.Companion.getParenthesisToken
import generators.TokenGenerator.Companion.getSpaceToken
import generators.TokenGenerator.Companion.getTypeStrategy
import generators.TokenGenerator.Companion.getValueToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import lexer.exceptions.NoTokenException
import lexer.generators.IndexGenerator.Companion.defineIndex
import lexer.interfaces.LexerI
import strategies.TokenStrategy.Companion.constStrategy
import strategies.TokenStrategy.Companion.finalStrategy
import strategies.TokenStrategy.Companion.identifierStrategy
import strategies.TokenStrategy.Companion.letStrategy
import strategies.TokenStrategy.Companion.operationStrategy
import strategies.TokenStrategy.Companion.parenthesisStrategy
import strategies.TokenStrategy.Companion.printStrategy
import strategies.TokenStrategy.Companion.spaceStrategy
import strategies.TokenStrategy.Companion.typeStrategy
import strategies.TokenStrategy.Companion.valueStrategy

class Lexer : LexerI {

    override fun getTokens(codeFlow: Flow<String>): Flow<Token> = flow {
        codeFlow.flatMapConcat { line ->
            defineTokens(line).asFlow()
        }.collect { token ->
            emit(token)
        }
    }

    fun defineTokens(line: String): ArrayList<Token> {
        val tokens: ArrayList<Token> = ArrayList()
        var index = 0
        while (index <= line.length - 1) {
            tokens.add(defineToken(line, index))
            index = defineIndex(tokens, index)
        }
        return tokens
    }

    private fun defineToken(line: String, index: Int): Token {
        if (spaceStrategy(line, index)) return getSpaceToken()
        if (letStrategy(line, index)) return getLetToken()
        if (constStrategy(line, index)) return getConstToken()
        if (typeStrategy(line, index)) return getTypeStrategy(line, index)
        if (operationStrategy(line, index)) return getOperationStrategy(line, index)
        if (finalStrategy(line, index)) return getFinalToken()
        if (printStrategy(line, index)) return getMethodPrintToken()
        if (valueStrategy(line, index)) return getValueToken(line, index)
        if (identifierStrategy(line, index)) return getIdentifierToken(line, index)
        if (parenthesisStrategy(line, index)) return getParenthesisToken(line, index)
        throw NoTokenException("No token with this expression " + line[index])
    }
}
