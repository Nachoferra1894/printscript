import fixtures.getFlowFromTokenList
import fixtures.node0
import fixtures.node1
import fixtures.node2
import fixtures.node3
import fixtures.node4
import fixtures.node5
import fixtures.tokenList0
import fixtures.tokenList1
import fixtures.tokenList2
import fixtures.tokenList3
import fixtures.tokenList4
import fixtures.tokenList5

import interfaces.Parser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParserTest {
    private val parser: Parser = V1Parser()

    @Test
    fun testSimpleAssignationToken() {
        // Statement: a = 42;
        val tokenList = tokenList0
        val node = node0

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList))
        assertEquals(node.toString(), astNode.toString())
    }

    @Test
    fun testSimpleSumToken() {
        // Statement: 22 + 20;
        val tokenList = tokenList1
        val node = node1

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList))
        assertEquals(node.toString(), astNode.toString())
    }

    @Test
    fun testDeclarationForString() {
        // Statement: let b: string = "Hello, world!";
        val tokenList = tokenList2
        val node = node2

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList))
        assertEquals(node.toString(), astNode.toString())
    }

    @Test
    fun testDeclarationForIntAndExpression() {
        // Statement: let c: number = 3 + 4 * 5;
        val tokenList = tokenList3
        val node = node3

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList))
        assertEquals(node.toString(), astNode.toString())
    }

    @Test
    fun testPrint() {
        // Statement: print("Hello, world!");
        val tokenList = tokenList4
        val node = node4

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList))
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

        val astNode = parser.parseTokens(getFlowFromTokenList(tokenList))
        assertEquals(node.toString(), astNode.toString())
    }
}
