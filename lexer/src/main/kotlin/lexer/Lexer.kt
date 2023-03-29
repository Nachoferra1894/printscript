package lexer.lexer

import Token
import generators.TokenGenerator.Companion.getFinalToken
import generators.TokenGenerator.Companion.getIdentifierToken
import generators.TokenGenerator.Companion.getLetToken
import generators.TokenGenerator.Companion.getMethodPrintToken
import generators.TokenGenerator.Companion.getOperationStrategy
import generators.TokenGenerator.Companion.getParenthesisToken
import generators.TokenGenerator.Companion.getSpaceToken
import generators.TokenGenerator.Companion.getTypeStrategy
import generators.TokenGenerator.Companion.getValueToken
import lexer.exceptions.NoTokenException
import lexer.generators.IndexGenerator.Companion.defineIndex
import lexer.interfaces.LexerI
import lexer.strategies.TokenStrategy.Companion.finalStrategy
import lexer.strategies.TokenStrategy.Companion.identifierStrategy
import lexer.strategies.TokenStrategy.Companion.letStrategy
import lexer.strategies.TokenStrategy.Companion.operationStrategy
import lexer.strategies.TokenStrategy.Companion.parenthesisStrategy
import lexer.strategies.TokenStrategy.Companion.printStrategy
import lexer.strategies.TokenStrategy.Companion.spaceStrategy
import lexer.strategies.TokenStrategy.Companion.typeStrategy
import lexer.strategies.TokenStrategy.Companion.valueStrategy

class Lexer : LexerI {

    override fun getTokens(code: String): ArrayList<Token> {
        var tokens: ArrayList<Token> = ArrayList()
        var lines = code.lines()
        lines.forEach {
            tokens.addAll(defineTokens(it))
        }
        return tokens
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
        if (typeStrategy(line, index)) return getTypeStrategy(line, index)
        if (operationStrategy(line, index)) return getOperationStrategy(line, index)
        if (finalStrategy(line, index)) return getFinalToken()
        if (printStrategy(line, index)) return getMethodPrintToken(line, index)
        if (valueStrategy(line, index)) return getValueToken(line, index)
        if (identifierStrategy(line, index)) return getIdentifierToken(line, index)
        if (parenthesisStrategy(line, index)) return getParenthesisToken(line, index)
        throw NoTokenException("No token with this expression " + line[index])
    }
}
