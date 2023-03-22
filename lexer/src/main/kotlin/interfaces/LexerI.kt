package lexer.interfaces

import Token

interface LexerI {
    fun getTokens(code: String): ArrayList<Token>
}
