package lexer

import lexer.generators.TokenGenerator.Companion.getFinalToken
import lexer.generators.TokenGenerator.Companion.getIdentifierToken
import lexer.generators.TokenGenerator.Companion.getLetToken
import lexer.generators.TokenGenerator.Companion.getOperationStrategy
import lexer.generators.TokenGenerator.Companion.getTypeStrategy
import lexer.generators.TokenGenerator.Companion.getValueToken
import lexer.strategies.TokenStrategy.Companion.finalStrategy
import lexer.strategies.TokenStrategy.Companion.identifierStrategy
import lexer.strategies.TokenStrategy.Companion.letStrategy
import lexer.strategies.TokenStrategy.Companion.operationStrategy
import lexer.strategies.TokenStrategy.Companion.typeStrategy
import lexer.strategies.TokenStrategy.Companion.valueStrategy
import lexer.exceptions.NoTokenException
import lexer.interfaces.LexerI
class Lexer: LexerI {

    override fun getTokens(code: String): ArrayList<Token> {
        var tokens: ArrayList<Token> = ArrayList()
        var lines = code.lines()
        lines.forEach {
            tokens.addAll(defineTokens(it))
        }
        return tokens
    }
    private fun defineTokens(line: String) : ArrayList<Token>{
        var tokens: ArrayList<Token> = ArrayList()
        for (i in line.indices) {
            tokens.add(defineToken(line, i))
        }
        return tokens
    }

    private fun defineToken(line: String, index: Int) : Token {
        if(letStrategy(line,index)) return getLetToken()
        if(typeStrategy(line, index)) return getTypeStrategy(line, index)
        if(operationStrategy(line, index)) return getOperationStrategy(line, index)
        if(finalStrategy(line, index)) return getFinalToken()
        if(valueStrategy(line, index)) return getValueToken(line, index)
        if(identifierStrategy(line, index)) return getIdentifierToken(line, index)
        throw NoTokenException("No token with this expression" + line[index])
    }
 }