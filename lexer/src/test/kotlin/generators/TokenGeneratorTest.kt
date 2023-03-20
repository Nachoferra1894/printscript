package generators

import lexer.PrototypeType
import lexer.Token
import lexer.generators.TokenGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TokenGeneratorTest {
    private val numberLine = "let a: number = 12;"
    private val stringLine = "let a: string = \"HI!\";"

    @Test
    fun testGetValueToken(){
        val indexCorrect = 16
        val generatedToken : Token = TokenGenerator.getValueToken(numberLine, indexCorrect)
        assertEquals(PrototypeType.NUMBER, generatedToken.prototypeType)
    }
}