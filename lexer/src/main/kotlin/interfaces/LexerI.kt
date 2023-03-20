package lexer.interfaces

import lexer.Token

interface LexerI {
    fun getTokens(code: String): ArrayList<Token>
}
