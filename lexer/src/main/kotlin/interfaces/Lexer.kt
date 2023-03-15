package lexer.interfaces

import lexer.Token

interface Lexer {
    fun getTokens(code: String): ArrayList<Token>
}
