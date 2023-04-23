package strategiesFormatter

import configuration.ConfigClasses
import configuration.SpaceAfterAssignation
import configuration.SpaceBeforeAssignation
import expresions.types.Variable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import strategiesFormatter.AssigmentStrategy.Companion.defineValue
import types.AssignmentNode

class AssigmentStrategyTest {

    val node = AssignmentNode("a", Variable("42", PrototypeType.NUMBER, 1), 1)

    @Test
    fun testAssigmentWithBefore() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("a =42", defineValue(configClasses, node))
    }

    @Test
    fun testAssigmentWithAfter() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("a= 42", defineValue(configClasses, node))
    }

    @Test
    fun testAssigmentWithAfterAndBefore() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("a = 42", defineValue(configClasses, node))
    }

    @Test
    fun testAssigmentWithOut() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        Assertions.assertEquals("a=42", defineValue(configClasses, node))
    }
}
