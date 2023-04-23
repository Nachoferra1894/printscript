package lexer

import Token
import lexer.lexer.Lexer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import version.V1

class LexerTestVersion1 {
    private val numberLine = "let a: number = 12;"
    private val stringLine = "let aS12 : string = \"HI!\";"
    private val expression = "a/b ; "
    private val printLine = "println(\"HI!\" + a);"
    private val lexer = Lexer()

    @Test
    fun testNumberLine() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(numberLine, 1, V1())
        val expectedTokens: ArrayList<Token> = ArrayList()
        expectedTokens.add(Token(PrototypeType.LET, null, 0, 3, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 3, 4, 1))
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "a", 4, 5, 1))
        expectedTokens.add(Token(PrototypeType.COLON, null, 5, 6, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 6, 7, 1))
        expectedTokens.add(Token(PrototypeType.NUMBER_TYPE, null, 7, 13, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 13, 14, 1))
        expectedTokens.add(Token(PrototypeType.EQUALS, null, 14, 15, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 15, 16, 1))
        expectedTokens.add(Token(PrototypeType.NUMBER, "12", 16, 18, 1))
        expectedTokens.add(Token(PrototypeType.SEMICOLON, null, 18, 19, 1))
        assertEquals(expectedTokens, actualTokens)
    }

    @Test
    fun testStringLine() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(stringLine, 1, V1())
        val expectedTokens: ArrayList<Token> = ArrayList()
        expectedTokens.add(Token(PrototypeType.LET, null, 0, 3, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 3, 4, 1))
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "aS12", 4, 8, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 8, 9, 1))
        expectedTokens.add(Token(PrototypeType.COLON, null, 9, 10, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 10, 11, 1))
        expectedTokens.add(Token(PrototypeType.STRING_TYPE, null, 11, 17, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 17, 18, 1))
        expectedTokens.add(Token(PrototypeType.EQUALS, null, 18, 19, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 19, 20, 1))
        expectedTokens.add(Token(PrototypeType.STRING, "HI!", 20, 23, 1))
        expectedTokens.add(Token(PrototypeType.SEMICOLON, null, 25, 26, 1))
        assertEquals(expectedTokens, actualTokens)
    }

    @Test
    fun testExpressionLine() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(expression, 1, V1())
        val expectedTokens: ArrayList<Token> = ArrayList()
        println(actualTokens)
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "a", 0, 1, 1))
        expectedTokens.add(Token(PrototypeType.DIVISION, null, 1, 2, 1))
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "b", 2, 3, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 3, 4, 1))
        expectedTokens.add(Token(PrototypeType.SEMICOLON, null, 4, 5, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 5, 6, 1))
        assertEquals(expectedTokens, actualTokens)
    }

    @Test
    fun testPrintLine() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(printLine, 1, V1())
        val expectedTokens: ArrayList<Token> = ArrayList()
        expectedTokens.add(Token(PrototypeType.METHOD_PRINT, null, 0, 7, 1))
        expectedTokens.add(Token(PrototypeType.OPEN_PARENTHESIS, null, 7, 8, 1))
        expectedTokens.add(Token(PrototypeType.STRING, "HI!", 8, 11, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 13, 14, 1))
        expectedTokens.add(Token(PrototypeType.PLUS, null, 14, 15, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 15, 16, 1))
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "a", 16, 17, 1))
        expectedTokens.add(Token(PrototypeType.CLOSE_PARENTHESIS, null, 17, 18, 1))
        expectedTokens.add(Token(PrototypeType.SEMICOLON, null, 18, 19, 1))
        assertEquals(expectedTokens.toString(), actualTokens.toString())
    }
}
