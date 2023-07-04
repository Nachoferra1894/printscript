package strategies

import configurationLinter.ConfigClassesLinter
import configurationLinter.ReadInputNormal
import configurationLinter.ReadInputOperations
import expresions.Operator
import expresions.types.Operation
import expresions.types.ReadInputExp
import expresions.types.Variable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import types.VariableDeclarationNode

class ExpressionStrategyTest {
    private val readConfig = ExpressionStrategy()
    private val nodeOperation = VariableDeclarationNode(
        "a",
        "string",
        ReadInputExp(
            Operation(
                Variable("a", PrototypeType.IDENTIFIER, 0),
                Operator.SUM,
                Variable("b", PrototypeType.IDENTIFIER, 0),
                0
            ),
            0
        ),
        0
    )

    private val nodeNormal = VariableDeclarationNode(
        "a",
        "string",
        ReadInputExp(
            Variable("a", PrototypeType.IDENTIFIER, 0),
            0
        ),
        0
    )

    @Test
    fun testMessage() {
        Assertions.assertEquals("Incorrect readInput format [line: 0 ]", readConfig.getIncorrectLine(nodeOperation.getValue()!!))
    }

    @Test
    fun testIsOperationExpression() {
        val configs: ArrayList<ConfigClassesLinter> = ArrayList()
        configs.add(ReadInputOperations())
        Assertions.assertTrue(readConfig.checkContent(nodeOperation.getValue()!!, configs))
        Assertions.assertTrue(readConfig.checkContent(nodeNormal.getValue()!!, configs))
    }

    @Test
    fun testIsOperationNormal() {
        val configs: ArrayList<ConfigClassesLinter> = ArrayList()
        configs.add(ReadInputNormal())
        Assertions.assertFalse(readConfig.checkContent(nodeOperation.getValue()!!, configs))
        Assertions.assertTrue(readConfig.checkContent(nodeNormal.getValue()!!, configs))
    }
}
