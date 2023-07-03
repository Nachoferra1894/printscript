package linter

import configurationLinter.ConfigClassesLinter
import configurationLinter.PrintNormal
import configurationLinter.SnakeCase
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import generators.LinterVisitorV2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import types.AssignmentNode
import types.IfNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class LinterV2Test {
    @Test
    fun testLinterWithNormalPrintAndSnakeCase() {
        val configs: ArrayList<ConfigClassesLinter> = ArrayList()
        configs.add(PrintNormal())
        configs.add(SnakeCase())
        val linter = LinterVisitorV2(configs)
        val node = IfNode(
            Variable("a", PrototypeType.IDENTIFIER, 0),
            0,
            ParentNode(
                listOf(
                    VariableDeclarationNode(
                        "a_a",
                        "number",
                        Operation(
                            Variable("1", PrototypeType.NUMBER, 1),
                            Operator.SUB,
                            Operation(
                                Variable("2", PrototypeType.NUMBER, 1),
                                Operator.SUB,
                                Variable("3", PrototypeType.NUMBER, 1),
                                1
                            ),
                            1
                        ),
                        1
                    ),
                    VariableDeclarationNode("b", "number", 2),
                    AssignmentNode("b", Operation(Variable("a", PrototypeType.IDENTIFIER, 3), Operator.SUM, Variable("1", PrototypeType.NUMBER, 3), 3), 3),
                    PrintNode(Variable("a", PrototypeType.IDENTIFIER, 4), 4)
                )
            )
        )
        linter.visitIfNode(node)
        Assertions.assertEquals("", linter.getLines())
        val nodeIncorrect = IfNode(
            Variable("a", PrototypeType.IDENTIFIER, 0),
            0,
            ParentNode(
                listOf(
                    VariableDeclarationNode(
                        "aAa",
                        "number",
                        Operation(
                            Variable("1", PrototypeType.NUMBER, 1),
                            Operator.SUB,
                            Operation(
                                Variable("2", PrototypeType.NUMBER, 1),
                                Operator.SUB,
                                Variable("3", PrototypeType.NUMBER, 1),
                                1
                            ),
                            1
                        ),
                        1
                    ),
                    VariableDeclarationNode("b", "number", 2),
                    AssignmentNode("b", Operation(Variable("a", PrototypeType.IDENTIFIER, 3), Operator.SUM, Variable("1", PrototypeType.NUMBER, 3), 3), 3),
                    PrintNode(Operation(Variable("a", PrototypeType.IDENTIFIER, 4), Operator.SUM, Variable("b", PrototypeType.IDENTIFIER, 4), 4), 4)
                )
            )
        )
        linter.visitIfNode(nodeIncorrect)
        Assertions.assertEquals(
            "Incorrect identifier format [line: 1 ]\n" +
                "Incorrect println format [line: 4 ]",
            linter.getLines()
        )
    }
}
