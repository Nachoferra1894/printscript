package lexer

interface Lexer {
    fun getTokens(code: String): ArrayList<Token>
}
