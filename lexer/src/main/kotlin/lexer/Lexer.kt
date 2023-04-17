package lexer.lexer

import Token
import languageDefinitions.Strategies
import lexer.generators.IndexGenerator.Companion.defineIndex
import lexer.interfaces.LexerI

class Lexer : LexerI {

    override fun getTokens(code: String): ArrayList<Token> {
        val tokens: ArrayList<Token> = ArrayList()
        val lines = code.lines()
        lines.forEach {
            tokens.addAll(defineTokens(it))
        }
        return tokens
    }
    fun defineTokens(line: String): ArrayList<Token> {
        val strategies: Strategies = Strategies()
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
