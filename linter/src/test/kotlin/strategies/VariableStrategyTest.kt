package strategies

import configuration.CamelCase
import configuration.ConfigClasses
import configuration.SnakeCase
import expresions.types.Variable
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import types.VariableDeclarationNode

class VariableStrategyTest {
    @Test
    fun testMessage() {
        val readConfig = VariableStrategy()
        val node = VariableDeclarationNode("b", "string", Variable("Hello, world!", PrototypeType.STRING, 1), 1)
        assertEquals("Incorrect identifier format [line: 1 ]", readConfig.getIncorrectLine(node))
    }

    @Test
    fun testIsCamelCase() {
        val readConfig = VariableStrategy()
        val configs: ArrayList<ConfigClasses> = ArrayList()
        configs.add((CamelCase()))
        val node = VariableDeclarationNode("bAa", "string", Variable("Hello, world!", PrototypeType.STRING, 1), 1)
        assertTrue(readConfig.checkIdentifierCondition(node, configs))
        val node1 = VariableDeclarationNode("b_a", "string", Variable("Hello, world!", PrototypeType.STRING, 1), 1)
        assertFalse(readConfig.checkIdentifierCondition(node1, configs))
    }

    @Test
    fun testIsSnakeCase() {
        val readConfig = VariableStrategy()
        val configs: ArrayList<ConfigClasses> = ArrayList()
        configs.add((SnakeCase()))
        val node = VariableDeclarationNode("bAa", "string", Variable("Hello, world!", PrototypeType.STRING, 1), 1)
        val node1 = VariableDeclarationNode("b_a", "string", Variable("Hello, world!", PrototypeType.STRING, 1), 1)
        assertFalse(readConfig.checkIdentifierCondition(node, configs))
        assertTrue(readConfig.checkIdentifierCondition(node1, configs))
    }
}
