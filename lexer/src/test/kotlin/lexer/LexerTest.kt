package lexer

import Token
import lexer.lexer.Lexer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.exp

class LexerTest {
    private val numberLine = "let a: number = 12;"
    private val stringLine = "let aS12 : string = \"HI!\";"
    private val expression = "a/b ; "

    private val lexer = Lexer()

    @Test
    fun testNumberLine() {
        var actualTokens: ArrayList<Token> = lexer.defineTokens(numberLine)
        var expectedTokens : ArrayList<Token> = ArrayList()
        expectedTokens.add(Token(PrototypeType.LET, null) )
        expectedTokens.add(Token(PrototypeType.SPACE, null) )
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "a"))
        expectedTokens.add(Token(PrototypeType.COLON, null))
        expectedTokens.add(Token(PrototypeType.SPACE, null))
        expectedTokens.add(Token(PrototypeType.NUMBER_TYPE, null))
        expectedTokens.add(Token(PrototypeType.SPACE, null))
        expectedTokens.add(Token(PrototypeType.ASSIGNATION, null))
        expectedTokens.add(Token(PrototypeType.SPACE, null))
        expectedTokens.add(Token(PrototypeType.NUMBER, "12"))
        expectedTokens.add(Token(PrototypeType.SEMICOLON, null))
        assertEquals(expectedTokens, actualTokens)
    }

    @Test
    fun testStringLine() {
        var actualTokens: ArrayList<Token> = lexer.defineTokens(stringLine)
        var expectedTokens : ArrayList<Token> = ArrayList()
        expectedTokens.add(Token(PrototypeType.LET, null) )
        expectedTokens.add(Token(PrototypeType.SPACE, null) )
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "aS12"))
        expectedTokens.add(Token(PrototypeType.SPACE, null) )
        expectedTokens.add(Token(PrototypeType.COLON, null))
        expectedTokens.add(Token(PrototypeType.SPACE, null))
        expectedTokens.add(Token(PrototypeType.STRING_TYPE, null))
        expectedTokens.add(Token(PrototypeType.SPACE, null))
        expectedTokens.add(Token(PrototypeType.ASSIGNATION, null))
        expectedTokens.add(Token(PrototypeType.SPACE, null))
        expectedTokens.add(Token(PrototypeType.STRING, "HI!"))
        expectedTokens.add(Token(PrototypeType.SEMICOLON, null))
        assertEquals(expectedTokens, actualTokens)
    }

    @Test
    fun testExpressionLine() {
        var actualTokens: ArrayList<Token> = lexer.defineTokens(expression)
        var expectedTokens : ArrayList<Token> = ArrayList()
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "a"))
        expectedTokens.add(Token(PrototypeType.DIVISION, null) )
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "b"))
        expectedTokens.add(Token(PrototypeType.SPACE, null))
        expectedTokens.add(Token(PrototypeType.SEMICOLON, null))
        expectedTokens.add(Token(PrototypeType.SPACE, null))
        assertEquals(expectedTokens, actualTokens)
    }
}
