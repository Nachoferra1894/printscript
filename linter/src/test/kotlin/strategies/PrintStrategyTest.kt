package strategies

import configurationLinter.ConfigClassesLinter
import configurationLinter.PrintNormal
import configurationLinter.PrintOperations
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import types.PrintNode

class PrintStrategyTest {

    @Test
    fun testMessage() {
        val readConfig = LinterPrintStrategy()
        val node = PrintNode(Operation(Variable("3", PrototypeType.NUMBER, 1), Operator.SUM, Operation(Variable("4", PrototypeType.NUMBER, 1), Operator.MUL, Variable("5", PrototypeType.NUMBER, 1), 1), 1), 1)
        assertEquals("Incorrect println format [line: 1 ]", readConfig.getIncorrectLine(node))
    }

    @Test
    fun testIsNormalPrint() {
        val readConfig = LinterPrintStrategy()
        val configs: ArrayList<ConfigClassesLinter> = ArrayList()
        configs.add(PrintNormal())
        val node = PrintNode(Operation(Variable("3", PrototypeType.NUMBER, 1), Operator.SUM, Operation(Variable("4", PrototypeType.NUMBER, 1), Operator.MUL, Variable("5", PrototypeType.NUMBER, 1), 1), 1), 1)
        val node1 = PrintNode(Variable("Hello, world!", PrototypeType.STRING, 1), 1)
        assertFalse(readConfig.checkContent(node, configs))
        assertTrue(readConfig.checkContent(node1, configs))
    }

    @Test
    fun testIsOperationPrint() {
        val readConfig = LinterPrintStrategy()
        val configs: ArrayList<ConfigClassesLinter> = ArrayList()
        configs.add(PrintOperations())
        val node = PrintNode(Operation(Variable("3", PrototypeType.NUMBER, 1), Operator.SUM, Operation(Variable("4", PrototypeType.NUMBER, 1), Operator.MUL, Variable("5", PrototypeType.NUMBER, 1), 1), 1), 1)
        val node1 = PrintNode(Variable("Hello, world!", PrototypeType.STRING, 1), 1)
        assertTrue(readConfig.checkContent(node, configs))
        assertFalse(readConfig.checkContent(node1, configs))
    }
}
