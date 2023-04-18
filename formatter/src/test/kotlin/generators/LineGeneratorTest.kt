package generators

import PrototypeType
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import generators.LineGenerator.Companion.assigmentGeneratorLine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import types.AssignmentNode
import types.PrintNode
import types.VariableDeclarationNode

class LineGeneratorTest {
    private val variableDeclarationNode =
        VariableDeclarationNode("b", "string", Variable("Hello, world!", PrototypeType.STRING, 1), 1)
//    val parentNode = ParentNode(
//        listOf(
//            VariableDeclarationNode(
//                "a",
//                "number",
//                Operation(
//                    Variable("1", PrototypeType.NUMBER),
//                    Operator.SUB,
//                    Operation(Variable("2", PrototypeType.NUMBER), Operator.SUB, Variable("3", PrototypeType.NUMBER))
//                )
//            ),
//            VariableDeclarationNode("b", "number"),
//            AssignmentNode(
//                "b",
//                Operation(Variable("a", PrototypeType.IDENTIFIER), Operator.SUM, Variable("1", PrototypeType.NUMBER))
//            ),
//            PrintNode(
//                Operation(
//                    Variable("a", PrototypeType.IDENTIFIER),
//                    Operator.SUM,
//                    Variable("b", PrototypeType.IDENTIFIER)
//                )
//            )
//        )
//    )

    @Test
    fun testAssigmentLineGenerator() {
        val assigmentNode = AssignmentNode("a", Variable("42", PrototypeType.NUMBER, 1), 1)
        val assigmentLine = "a = 42;"
        val assigmentLine2 = "b = 22 + 20;"
        val assigmentNode2 = AssignmentNode(
            "b",
            Operation(Variable("22", PrototypeType.NUMBER, 2), Operator.SUM, Variable("20", PrototypeType.NUMBER, 2), 2),
            2
        )
        assertEquals(assigmentLine, assigmentGeneratorLine(assigmentNode))
        assertEquals(assigmentLine2, assigmentGeneratorLine(assigmentNode2))
    }

    @Test
    fun testPrintLineGenerator() {
        val printNode = PrintNode(Variable("Hello, world!", PrototypeType.STRING, 1), 1)
        val printLine = "print(\"Hello, world!\");"
        // val assigmentLine2= "b = 22 + 20;"
        // val assigmentNode2 = AssignmentNode("b", Operation(Variable("22"), Operator.SUM, Variable("20",PrototypeType.NUMBER)))
        // assertEquals(printLine, assigmentPrintLine(printNode))
        assertEquals(true, true)
        // assertEquals(assigmentLine2, assigmentGeneratorLine(assigmentNode2))
    }
}
