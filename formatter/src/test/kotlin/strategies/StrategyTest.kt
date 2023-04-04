package strategies

import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import strategies.NodeStrategy.Companion.assigmentStrategy
import strategies.NodeStrategy.Companion.parentStrategy
import strategies.NodeStrategy.Companion.printStrategy
import strategies.NodeStrategy.Companion.variableDeclarationStrategy
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class StrategyTest {
    private val assigmentNode = AssignmentNode("a", Variable("42", PrototypeType.NUMBER))
    private val variableDeclarationNode = VariableDeclarationNode("b", "string", Variable("Hello, world!", PrototypeType.STRING))
    private val printNode = PrintNode(Variable("Hello, world!", PrototypeType.STRING))
    val parentNode = ParentNode(
        listOf(
            VariableDeclarationNode("a", "number", Operation(Variable("1", PrototypeType.NUMBER), Operator.SUB, Operation(Variable("2", PrototypeType.NUMBER), Operator.SUB, Variable("3", PrototypeType.NUMBER)))),
            VariableDeclarationNode("b", "number"),
            AssignmentNode("b", Operation(Variable("a", PrototypeType.IDENTIFIER), Operator.SUM, Variable("1", PrototypeType.NUMBER))),
            PrintNode(Operation(Variable("a", PrototypeType.IDENTIFIER), Operator.SUM, Variable("b", PrototypeType.IDENTIFIER)))
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
