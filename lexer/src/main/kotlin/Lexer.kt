package lexer

import lexer.TokenGenerator.Companion.getFinalToken
import lexer.TokenGenerator.Companion.getIdentifierToken
import lexer.TokenGenerator.Companion.getLetToken
import lexer.TokenGenerator.Companion.getOperationStrategy
import lexer.TokenGenerator.Companion.getTypeStrategy
import lexer.TokenGenerator.Companion.getValueToken
import lexer.TokenStrategy.Companion.finalStrategy
import lexer.TokenStrategy.Companion.identifierStrategy
import lexer.TokenStrategy.Companion.letStrategy
import lexer.TokenStrategy.Companion.operationStrategy
import lexer.TokenStrategy.Companion.typeStrategy
import lexer.TokenStrategy.Companion.valueStrategy
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