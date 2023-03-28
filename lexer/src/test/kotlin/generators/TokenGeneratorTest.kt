package generators

import Token
import lexer.generators.TokenGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TokenGeneratorTest {
    private val numberLine = "let a: number = 12;"
    private val stringLine = "let a: string = \"HI!\";"
    private val longIdentifier = "let asasasa: string = \"HI!\";"
    private val plusOperation = "a + a"
    private val subtractOperation = "a - a"
    private val multiplicationOperation = "a * a"
    private val divisionOperation = "a / a"

    @Test
    fun testGetValueTokenNumber() {
        val indexCorrect = 16
        val generatedToken: Token = TokenGenerator.getValueToken(numberLine, indexCorrect)
        assertEquals(PrototypeType.NUMBER, generatedToken.prototypeType)
        assertEquals("12", generatedToken.value)
    }

    @Test
    fun testGetValueTokenString() {
        val indexCorrect = 16
        val generatedToken: Token = TokenGenerator.getValueToken(stringLine, indexCorrect)
        assertEquals(PrototypeType.STRING, generatedToken.prototypeType)
        assertEquals("HI!", generatedToken.value)
    }

    @Test
    fun testGetLetToken() {
        val generatedToken: Token = TokenGenerator.getLetToken()
        assertEquals(PrototypeType.LET, generatedToken.prototypeType)
    }

    @Test
    fun testGetTypeStrategyString() {
        val indexCorrect = 7
        val generatedToken: Token = TokenGenerator.getTypeStrategy(stringLine, indexCorrect)
        assertEquals(PrototypeType.STRING_TYPE, generatedToken.prototypeType)
    }

    @Test
    fun testGetTypeStrategyNumber() {
        val indexCorrect = 7
        val generatedToken: Token = TokenGenerator.getTypeStrategy(numberLine, indexCorrect)
        assertEquals(PrototypeType.NUMBER_TYPE, generatedToken.prototypeType)
    }

    @Test
    fun testGetFinalStrategy() {
        val generatedToken: Token = TokenGenerator.getFinalToken()
        assertEquals(PrototypeType.SEMICOLON, generatedToken.prototypeType)
    }

    @Test
    fun testGetIndentifierStrategy() {
        val indexCorrect = 4
        val generatedToken: Token = TokenGenerator.getIdentifierToken(numberLine, indexCorrect)
        assertEquals(PrototypeType.IDENTIFIER, generatedToken.prototypeType)
        assertEquals("a", generatedToken.value)
    }

    @Test
    fun testGetIndentifierStrategyLong() {
        val indexCorrect = 4
        val generatedToken: Token = TokenGenerator.getIdentifierToken(longIdentifier, indexCorrect)
        assertEquals(PrototypeType.IDENTIFIER, generatedToken.prototypeType)
        assertEquals("asasasa", generatedToken.value)
    }

    @Test
    fun testGetOperationStrategy() {
        val indexAssignation = 14
        assertEquals(PrototypeType.ASSIGNATION, TokenGenerator.getOperationStrategy(numberLine, indexAssignation).prototypeType)
        val indexPlus = 2
        assertEquals(PrototypeType.PLUS, TokenGenerator.getOperationStrategy(plusOperation, indexPlus).prototypeType)
        val indexSubtract = 2
        assertEquals(PrototypeType.SUBTRACTION, TokenGenerator.getOperationStrategy(subtractOperation, indexSubtract).prototypeType)
        val indexMultiplication = 2
        assertEquals(PrototypeType.MULTIPLICATION, TokenGenerator.getOperationStrategy(multiplicationOperation, indexMultiplication).prototypeType)
        val indexDivision = 2
        assertEquals(PrototypeType.DIVISION, TokenGenerator.getOperationStrategy(divisionOperation, indexDivision).prototypeType)
    }
}
