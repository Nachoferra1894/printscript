import exceptions.WrongTokenException
import fixtures.getFlowFromTokenList
import fixtures.mutableNode6
import fixtures.node0
import fixtures.node1
import fixtures.node2
import fixtures.node3
import fixtures.node4
import fixtures.node5
import fixtures.node6
import fixtures.node7
import fixtures.tokenList0
import fixtures.tokenList1
import fixtures.tokenList2
import fixtures.tokenList3
import fixtures.tokenList4
import fixtures.tokenList5
import fixtures.tokenList6
import fixtures.tokenList7
import interfaces.Parser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import version.V1
import version.V2

class ParserTest {
    private val parser: Parser = V1Parser()

    @Test
    fun testSimpleAssignationToken() {
        // Statement: a = 42;
        val tokenList = tokenList0
        val node = node0

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList), V1())
        assertEquals(node.toString(), astNode.toString())
    }

    @Test
    fun testSimpleSumToken() {
        // Statement: 22 + 20;
        val tokenList = tokenList1
        val node = node1

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList), V1())
        assertEquals(node.toString(), astNode.toString())
    }

    @Test
    fun testDeclarationForString() {
        // Statement: let b: string = "Hello, world!";
        val tokenList = tokenList2
        val node = node2

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList), V1())
        assertEquals(node.toString(), astNode.toString())
    }

    @Test
    fun testDeclarationForIntAndExpression() {
        // Statement: let c: number = 3 + 4 * 5;
        val tokenList = tokenList3
        val node = node3

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList), V1())
        assertEquals(node.toString(), astNode.toString())
    }

    @Test
    fun testPrint() {
        // Statement: print("Hello, world!");
        val tokenList = tokenList4
        val node = node4

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList), V1())
        assertEquals(node.toString(), astNode.toString())
    }

    @Test
    fun testMultipleLines() {
        // Statement: let a: number = 1 - 2 - 3;
        // Statement: let b: number;
        // Statement: b = a + 1;
        // Statement: print(a + b);

        val tokenList = tokenList5
        val node = node5

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList), V1())
        assertEquals(node.toString(), astNode.toString())
    }

    @Test
    fun testSpaceTokensDontBreak() {
        // Statement: a =    42;
        val tokenList = tokenList7
        val node = node7

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList), V1())
        assertEquals(node.toString(), astNode.toString())
    }

    @Test
    fun testConstForV1ShouldBreak() {
        // Statement: const b: boolean = true;

        val tokenList = tokenList6

        try {
            parser.parseTokens(getFlowFromTokenList(tokenList), V1())
        } catch (e: Exception) {
            println(e)
            assertTrue(e is WrongTokenException)
            assertEquals("Token: ${PrototypeType.CONST} not compatible with node", e.message)
        }
    }

    @Test
    fun testBooleanForV1ShouldBreak() {
        // Statement: let b: boolean = true;
        val tokenList = listOf<Token>(Token(PrototypeType.LET, null, 0, 3, 0), *tokenList6.drop(1).toTypedArray())

        try {
            parser.parseTokens(getFlowFromTokenList(tokenList), V1())
        } catch (e: Exception) {
            println(e)
            assertTrue(e is WrongTokenException)
            assertEquals("Token: ${PrototypeType.BOOLEAN_TYPE} not compatible with node", e.message)
        }
    }

    @Test
    fun testConstAndBoolean() {
        // Statement: const b: boolean = true;

        val tokenList = tokenList6
        val node = node6
        val notNode = mutableNode6

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList), V2())
        assertEquals(node.toString(), astNode.toString())
        assertNotEquals(notNode.toString(), astNode.toString())
    }
}
