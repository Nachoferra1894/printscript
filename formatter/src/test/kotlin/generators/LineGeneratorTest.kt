package generators

import PrototypeType
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import generators.LineGenerator.Companion.assigmentGeneratorLine
import generators.LineGenerator.Companion.assigmentPrintLine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class LineGeneratorTest {
    private val variableDeclarationNode = VariableDeclarationNode("b", "string", Variable("Hello, world!", PrototypeType.STRING))
    val parentNode = ParentNode(
        listOf(
            VariableDeclarationNode("a", "number", Operation(Variable("1", PrototypeType.NUMBER), Operator.SUB, Operation(Variable("2", PrototypeType.NUMBER), Operator.SUB, Variable("3", PrototypeType.NUMBER)))),
            VariableDeclarationNode("b", "number"),
            AssignmentNode("b", Operation(Variable("a", PrototypeType.IDENTIFIER), Operator.SUM, Variable("1", PrototypeType.NUMBER))),
            PrintNode(Operation(Variable("a", PrototypeType.IDENTIFIER), Operator.SUM, Variable("b", PrototypeType.IDENTIFIER)))
        )
    )

    @Test
    fun testAssigmentLineGenerator() {
        val assigmentNode = AssignmentNode("a", Variable("42", PrototypeType.NUMBER))
        val assigmentLine = "a = 42;"
        val assigmentLine2 = "b = 22 + 20;"
        val assigmentNode2 = AssignmentNode("b", Operation(Variable("22", PrototypeType.NUMBER), Operator.SUM, Variable("20", PrototypeType.NUMBER)))
        assertEquals(assigmentLine, assigmentGeneratorLine(assigmentNode))
        assertEquals(assigmentLine2, assigmentGeneratorLine(assigmentNode2))
    }

    @Test
    fun testPrintLineGenerator() {
        val printNode = PrintNode(Variable("Hello, world!", PrototypeType.STRING))
        val printLine = "print(\"Hello, world!\");"
        // val assigmentLine2= "b = 22 + 20;"
        // val assigmentNode2 = AssignmentNode("b", Operation(Variable("22"), Operator.SUM, Variable("20",PrototypeType.NUMBER)))
        assertEquals(printLine, assigmentPrintLine(printNode))
        // assertEquals(assigmentLine2, assigmentGeneratorLine(assigmentNode2))
    }
}
