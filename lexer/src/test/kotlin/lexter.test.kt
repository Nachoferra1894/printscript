import lexer.Token
import lexer.interfaces.Lexer
import kotlin.test.Test
import lexer.V1Lexer
import kotlin.test.assertEquals

class LexerTest {
    val lexer: Lexer = V1Lexer()
    @Test
    fun testLexer() {
        val input = "let x: number = 5;"

//        val expectedTokens = listOf(
//            Token(TokenType.LET, "let"),
//            Token(TokenType.IDENTIFIER, "x"),
//            Token(TokenType.COLON, ":"),
//            Token(TokenType.NUMBER, "number"),
//            Token(TokenType.EQUALS, "="),
//            Token(TokenType.NUMBER_LITERAL, "5"),
//            Token(TokenType.SEMICOLON, ";")
//        )
//
//        for (expectedToken in expectedTokens) {
//            val token = lexer.nextToken()
//            assertEquals(expectedToken.type, token.type)
//            assertEquals(expectedToken.literal, token.literal)
//        }
    }
}
