package lexer

import Token
import lexer.lexer.Lexer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import version.V2

class LexerTestVersion2 {
    private val numberLine = "let a: number = 12;"
    private val stringLine = "let aS12 : string = \"HI!\";"
    private val expression = "a/b ; "
    private val printLine = "println(\"HI!\" + a);"
    private val booleanLineTrue = "let a: boolean = true;"
    private val booleanLineFalse = "let a: boolean = false;"

    private val constLine = "const a: number = 12;"
    private val ifAndElseStatement1 = "if(a){"
    private val ifAndElseStatement2 = "  b"
    private val ifAndElseStatement3 = "}else{"
    private val ifAndElseStatement4 = "  b"
    private val ifAndElseStatement5 = "}"
    private val readInputLine = "readInput(a);"

    private val lexer = Lexer()

    @Test
    fun testNumberLine() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(numberLine, 1, V2())
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
        Assertions.assertEquals(expectedTokens, actualTokens)
    }

    @Test
    fun testIfAndElseStatement() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(ifAndElseStatement1, 1, V2())
        actualTokens.addAll(lexer.defineTokens(ifAndElseStatement2, 2, V2()))
        actualTokens.addAll(lexer.defineTokens(ifAndElseStatement3, 3, V2()))
        actualTokens.addAll(lexer.defineTokens(ifAndElseStatement4, 4, V2()))
        actualTokens.addAll(lexer.defineTokens(ifAndElseStatement5, 5, V2()))
        val expectedTokens: ArrayList<Token> = ArrayList()
        expectedTokens.add(Token(PrototypeType.IF, null, 0, 2, 1))
        expectedTokens.add(Token(PrototypeType.OPEN_PARENTHESIS, null, 2, 3, 1))
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "a", 3, 4, 1))
        expectedTokens.add(Token(PrototypeType.CLOSE_PARENTHESIS, null, 4, 5, 1))
        expectedTokens.add(Token(PrototypeType.OPEN_BRACE, null, 5, 6, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 0, 1, 2))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 1, 2, 2))
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "b", 2, 3, 2))
        expectedTokens.add(Token(PrototypeType.CLOSE_BRACE, null, 0, 1, 3))
        expectedTokens.add(Token(PrototypeType.ELSE, null, 1, 5, 3))
        expectedTokens.add(Token(PrototypeType.OPEN_BRACE, null, 5, 6, 3))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 0, 1, 4))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 1, 2, 4))
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "b", 2, 3, 4))
        expectedTokens.add(Token(PrototypeType.CLOSE_BRACE, null, 0, 1, 5))
        Assertions.assertEquals(expectedTokens, actualTokens)
    }

    @Test
    fun testConstLine() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(constLine, 1, V2())
        val expectedTokens: ArrayList<Token> = ArrayList()
        expectedTokens.add(Token(PrototypeType.CONST, null, 0, 5, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 5, 6, 1))
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "a", 6, 7, 1))
        expectedTokens.add(Token(PrototypeType.COLON, null, 7, 8, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 8, 9, 1))
        expectedTokens.add(Token(PrototypeType.NUMBER_TYPE, null, 9, 15, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 15, 16, 1))
        expectedTokens.add(Token(PrototypeType.EQUALS, null, 16, 17, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 17, 18, 1))
        expectedTokens.add(Token(PrototypeType.NUMBER, "12", 18, 20, 1))
        expectedTokens.add(Token(PrototypeType.SEMICOLON, null, 20, 21, 1))
        Assertions.assertEquals(expectedTokens, actualTokens)
    }

    @Test
    fun testBooleanLineTrue() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(booleanLineTrue, 1, V2())
        val expectedTokens: ArrayList<Token> = ArrayList()
        expectedTokens.add(Token(PrototypeType.LET, null, 0, 3, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 3, 4, 1))
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "a", 4, 5, 1))
        expectedTokens.add(Token(PrototypeType.COLON, null, 5, 6, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 6, 7, 1))
        expectedTokens.add(Token(PrototypeType.BOOLEAN_TYPE, null, 7, 14, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 14, 15, 1))
        expectedTokens.add(Token(PrototypeType.EQUALS, null, 15, 16, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 16, 17, 1))
        expectedTokens.add(Token(PrototypeType.BOOLEAN, "true", 17, 21, 1))
        expectedTokens.add(Token(PrototypeType.SEMICOLON, null, 21, 22, 1))
        Assertions.assertEquals(expectedTokens, actualTokens)
    }

    @Test
    fun testBooleanLineFalse() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(booleanLineFalse, 1, V2())
        val expectedTokens: ArrayList<Token> = ArrayList()
        expectedTokens.add(Token(PrototypeType.LET, null, 0, 3, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 3, 4, 1))
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "a", 4, 5, 1))
        expectedTokens.add(Token(PrototypeType.COLON, null, 5, 6, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 6, 7, 1))
        expectedTokens.add(Token(PrototypeType.BOOLEAN_TYPE, null, 7, 14, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 14, 15, 1))
        expectedTokens.add(Token(PrototypeType.EQUALS, null, 15, 16, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 16, 17, 1))
        expectedTokens.add(Token(PrototypeType.BOOLEAN, "false", 17, 22, 1))
        expectedTokens.add(Token(PrototypeType.SEMICOLON, null, 22, 23, 1))
        Assertions.assertEquals(expectedTokens, actualTokens)
    }

    @Test
    fun testStringLine() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(stringLine, 1, V2())
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
        Assertions.assertEquals(expectedTokens, actualTokens)
    }

    @Test
    fun testExpressionLine() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(expression, 1, V2())
        val expectedTokens: ArrayList<Token> = ArrayList()
        println(actualTokens)
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "a", 0, 1, 1))
        expectedTokens.add(Token(PrototypeType.DIVISION, null, 1, 2, 1))
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "b", 2, 3, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 3, 4, 1))
        expectedTokens.add(Token(PrototypeType.SEMICOLON, null, 4, 5, 1))
        expectedTokens.add(Token(PrototypeType.SPACE, null, 5, 6, 1))
        Assertions.assertEquals(expectedTokens, actualTokens)
    }

    @Test
    fun testPrintLine() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(printLine, 1, V2())
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
        Assertions.assertEquals(expectedTokens.toString(), actualTokens.toString())
    }

    @Test
    fun testReadInputLine() {
        val actualTokens: ArrayList<Token> = lexer.defineTokens(readInputLine, 1, V2())
        val expectedTokens: ArrayList<Token> = ArrayList()
        expectedTokens.add(Token(PrototypeType.METHOD_READ_INPUT, null, 0, 9, 1))
        expectedTokens.add(Token(PrototypeType.OPEN_PARENTHESIS, null, 9, 10, 1))
        expectedTokens.add(Token(PrototypeType.IDENTIFIER, "a", 10, 11, 1))
        expectedTokens.add(Token(PrototypeType.CLOSE_PARENTHESIS, null, 11, 12, 1))
        expectedTokens.add(Token(PrototypeType.SEMICOLON, null, 12, 13, 1))
        Assertions.assertEquals(expectedTokens.toString(), actualTokens.toString())
    }
}
