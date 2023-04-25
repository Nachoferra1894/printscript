package strategiesFormatter

import configuration.ConfigClasses
import configuration.SpaceIndexedForIf
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import strategiesFormatter.IfStrategy.Companion.defineValue
import types.AssignmentNode
import types.IfNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class IfStrategyTest {
    val node = IfNode(
        Variable("a", PrototypeType.IDENTIFIER, 0),
        0,
        ParentNode(
            PrintNode(
                Variable("a is 1", PrototypeType.STRING, 0)
            )
        ),
        ParentNode(
            PrintNode(
                Variable("a is not 1", PrototypeType.STRING, 0)
            )
        )
    )

    val complexNode = IfNode(
        Variable("a", PrototypeType.IDENTIFIER, 0),
        0,
        ParentNode(
            listOf(
                VariableDeclarationNode(
                    "a",
                    "number",
                    Operation(
                        Variable("1", PrototypeType.NUMBER, 0),
                        Operator.SUB,
                        Operation(
                            Variable("2", PrototypeType.NUMBER, 0),
                            Operator.SUB,
                            Variable("3", PrototypeType.NUMBER, 0),
                            0
                        ),
                        0
                    ),
                    0
                ),
                VariableDeclarationNode("b", "number", 1),
                AssignmentNode(
                    "b",
                    Operation(
                        Variable("a", PrototypeType.IDENTIFIER, 2),
                        Operator.SUM,
                        Variable("1", PrototypeType.NUMBER, 2),
                        2
                    ),
                    2
                ),
                PrintNode(
                    Operation(
                        Variable("a", PrototypeType.IDENTIFIER, 3),
                        Operator.SUM,
                        Variable("b", PrototypeType.IDENTIFIER, 3),
                        3
                    ),
                    3
                )
            )
        )
    )

    @Test
    fun testPrintlnWith1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add((SpaceIndexedForIf(1)))
        val expected = "if (a) {\n" +
            " println(\"a is 1\");\n" +
            "}else{\n" +
            " println(\"a is not 1\");\n" +
            "}"
        assertEquals(expected, defineValue(configClasses, node))
    }

    @Test
    fun testPrintlnWith2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add((SpaceIndexedForIf(2)))
        val expected = "if (a) {\n" +
            "  println(\"a is 1\");\n" +
            "}else{\n" +
            "  println(\"a is not 1\");\n" +
            "}"
        assertEquals(expected, defineValue(configClasses, node))
    }

    @Test
    fun testPrintlnWith0() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add((SpaceIndexedForIf(0)))
        val expected = "if (a) {\n" +
            "println(\"a is 1\");\n" +
            "}else{\n" +
            "println(\"a is not 1\");\n" +
            "}"
        assertEquals(expected, defineValue(configClasses, node))
    }

    @Test
    fun testComplexNodeWith1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add((SpaceIndexedForIf(1)))
        val expected = "if (a) {\n" +
            " let a:number=1 - 2 - 3;\n" +
            " let b:number;\n" +
            " b=a + 1;\n" +
            " println(a + b);\n" +
            "}"
        assertEquals(expected, defineValue(configClasses, complexNode))
    }
}
