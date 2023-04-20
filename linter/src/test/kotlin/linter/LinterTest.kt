package linter

import configuration.CamelCase
import configuration.ConfigClasses
import configuration.PrintNormal
import configuration.PrintOperations
import configuration.SnakeCase
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import generators.LinterVisitor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class LinterTest {
    // PrintNode(Operation(Variable("a", PrototypeType.IDENTIFIER, 4), Operator.SUM, Variable("b", PrototypeType.IDENTIFIER, 4), 4), 4)
    // VariableDeclarationNode("b", "number", 2),
    @Test
    fun testLinterWithNormalPrintAndCamelCase() {
        val configs: ArrayList<ConfigClasses> = ArrayList()
        configs.add(PrintNormal())
        configs.add((CamelCase()))
        val linter = LinterVisitor(configs)
        val node = ParentNode(
            listOf(
                VariableDeclarationNode(
                    "a",
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
        linter.visitParentNode(node)
        assertEquals("", linter.getLines())
        val incorrectNode = ParentNode(
            listOf(
                VariableDeclarationNode(
                    "a_A",
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
        linter.visitParentNode(incorrectNode)
        assertEquals("Incorrect identifier format [line: 1 ]\nIncorrect println format [line: 4 ]", linter.getLines())
    }

    @Test
    fun testLinterWithOperationPrintAndCamelCase() {
        val configs: ArrayList<ConfigClasses> = ArrayList()
        configs.add(PrintOperations())
        configs.add((CamelCase()))
        val linter = LinterVisitor(configs)
        val node = ParentNode(
            listOf(
                VariableDeclarationNode(
                    "a",
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
        linter.visitParentNode(node)
        assertEquals("", linter.getLines())
        val incorrectNode = ParentNode(
            listOf(
                VariableDeclarationNode(
                    "a_A",
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
        linter.visitParentNode(incorrectNode)
        assertEquals("Incorrect identifier format [line: 1 ]\nIncorrect println format [line: 4 ]", linter.getLines())
    }

    @Test
    fun testLinterWithNormalPrintAndSnakeCase() {
        val configs: ArrayList<ConfigClasses> = ArrayList()
        configs.add(PrintNormal())
        configs.add((SnakeCase()))
        val linter = LinterVisitor(configs)
        val node = ParentNode(
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
        linter.visitParentNode(node)
        assertEquals("", linter.getLines())
        val incorrectNode = ParentNode(
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
        linter.visitParentNode(incorrectNode)
        assertEquals("Incorrect identifier format [line: 1 ]\nIncorrect println format [line: 4 ]", linter.getLines())
    }

    @Test
    fun testLinterWithOperationPrintAndSnakeCase() {
        val configs: ArrayList<ConfigClasses> = ArrayList()
        configs.add(PrintOperations())
        configs.add((SnakeCase()))
        val linter = LinterVisitor(configs)
        val node = ParentNode(
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
                PrintNode(Operation(Variable("a", PrototypeType.IDENTIFIER, 4), Operator.SUM, Variable("b", PrototypeType.IDENTIFIER, 4), 4), 4)
            )
        )
        linter.visitParentNode(node)
        assertEquals("", linter.getLines())
        val incorrectNode = ParentNode(
            listOf(
                VariableDeclarationNode(
                    "AaA",
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
        linter.visitParentNode(incorrectNode)
        assertEquals("Incorrect identifier format [line: 1 ]\nIncorrect println format [line: 4 ]", linter.getLines())
    }
}
