import org.junit.Test
import kotlin.test.assertEquals

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