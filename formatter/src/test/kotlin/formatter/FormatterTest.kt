package formatter

import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import fromatter.Formatter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class FormatterTest {
    private val formatter: Formatter = Formatter()

    @Test
    fun testFormatterWithParentNode() {
        val node = ParentNode(
            listOf(
                VariableDeclarationNode(
                    "a",
                    "number",
                    Operation(
                        Variable("1", PrototypeType.NUMBER),
                        Operator.SUB,
                        Operation(
                            Variable("2", PrototypeType.NUMBER),
                            Operator.SUB,
                            Variable("3", PrototypeType.NUMBER)
                        )
                    )
                ),
                VariableDeclarationNode("b", "number"),
                AssignmentNode("b", Operation(Variable("a", PrototypeType.IDENTIFIER), Operator.SUM, Variable("1", PrototypeType.NUMBER))),
                PrintNode(Operation(Variable("a", PrototypeType.IDENTIFIER), Operator.SUM, Variable("b", PrototypeType.IDENTIFIER)))
            )
        )
        val expectedResult = "let a: number = 1 - 2 - 3;\nlet b: number;\nb = a + 1;\nprint(a + b);"
        assertEquals(expectedResult, formatter.getFormattedCode(node))
    }

    @Test
    fun testFormatterWithAssigmentNode() {
        val node = AssignmentNode("a", Variable("42", PrototypeType.NUMBER))
        val expectedResult = "a = 42;"
        val node1 = AssignmentNode("b", Operation(Variable("22", PrototypeType.NUMBER), Operator.SUM, Variable("20", PrototypeType.NUMBER)))
        val expectedResult1 = "b = 22 + 20;"
        assertEquals(expectedResult, formatter.getFormattedCode(node))
        assertEquals(expectedResult1, formatter.getFormattedCode(node1))
    }

    @Test
    fun testFormatterWithDeclarationNode() {
        val node = VariableDeclarationNode("b", "string", Variable("Hello, world!", PrototypeType.STRING))
        val expectedResult = "let b: string = \"Hello, world!\";"
        val node1 = VariableDeclarationNode(
            "c",
            "number",
            Operation(Variable("3", PrototypeType.NUMBER), Operator.SUM, Operation(Variable("4", PrototypeType.NUMBER), Operator.MUL, Variable("5", PrototypeType.NUMBER)))
        )
        val expectedResult1 = "let c: number = 3 + 4 * 5;"
        assertEquals(expectedResult, formatter.getFormattedCode(node))
        assertEquals(expectedResult1, formatter.getFormattedCode(node1))
    }

    @Test
    fun testFormatterWithPrintNode() {
        val node = PrintNode(Variable("Hello, world!", PrototypeType.STRING))
        val expectedResult = "print(\"Hello, world!\");"
        val node1 = PrintNode(Operation(Variable("3", PrototypeType.NUMBER), Operator.SUM, Operation(Variable("4", PrototypeType.NUMBER), Operator.MUL, Variable("5", PrototypeType.NUMBER))))
        val expectedResult1 = "print(3 + 4 * 5);"
        assertEquals(expectedResult, formatter.getFormattedCode(node))
        assertEquals(expectedResult1, formatter.getFormattedCode(node1))
    }
    @Test
    fun testFormatterWithExpressionNode() {
        val node = Operation(Variable("22", PrototypeType.NUMBER), Operator.SUM, Variable("20", PrototypeType.NUMBER))
        val expectedResult = "22 + 20;"
        assertEquals(expectedResult, formatter.getFormattedCode(node))
        val node1 = Variable("22", PrototypeType.NUMBER)
        val expectedResult1 = "22;"
        val node2 = Variable("Hello", PrototypeType.STRING)
        val expectedResult2 = "\"Hello\";"
        assertEquals(expectedResult, formatter.getFormattedCode(node))
        assertEquals(expectedResult1, formatter.getFormattedCode(node1))
        assertEquals(expectedResult2, formatter.getFormattedCode(node2))
    }
}
