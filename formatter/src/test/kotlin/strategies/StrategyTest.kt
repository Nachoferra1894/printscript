package strategies

import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import strategies.NodesStrategy.Companion.assigmentStrategy
import strategies.NodesStrategy.Companion.parentStrategy
import strategies.NodesStrategy.Companion.printStrategy
import strategies.NodesStrategy.Companion.variableDeclarationStrategy
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class StrategyTest {
    private val assigmentNode = AssignmentNode("a", Variable("42"))
    private val variableDeclarationNode = VariableDeclarationNode("b", "string", Variable("Hello, world!"))
    private val printNode = PrintNode(Variable("Hello, world!"))
    val parentNode = ParentNode(
        listOf(
            VariableDeclarationNode("a", "number", Operation(Variable("1"), Operator.SUB, Operation(Variable("2"), Operator.SUB, Variable("3")))),
            VariableDeclarationNode("b", "number"),
            AssignmentNode("b", Operation(Variable("a"), Operator.SUM, Variable("1"))),
            PrintNode(Operation(Variable("a"), Operator.SUM, Variable("b")))
        )
    )

    @Test
    fun testAssigmentStrategy() {
        assertTrue(assigmentStrategy(assigmentNode))
        assertFalse(assigmentStrategy(variableDeclarationNode))
    }

    @Test
    fun testVariableStrategy() {
        assertTrue(variableDeclarationStrategy(variableDeclarationNode))
        assertFalse(variableDeclarationStrategy(assigmentNode))
    }

    @Test
    fun testPrintStrategy() {
        assertTrue(printStrategy(printNode))
        assertFalse(assigmentStrategy(variableDeclarationNode))
    }

    @Test
    fun testParentStrategy() {
        assertTrue(parentStrategy(parentNode))
        assertFalse(assigmentStrategy(variableDeclarationNode))
    }
}
