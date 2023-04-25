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

    val nodeNumber = AssignmentNode("a", Variable("42", PrototypeType.NUMBER, 1), 1)
    val nodeBoolean = AssignmentNode("a", Variable("true", PrototypeType.BOOLEAN, 1), 1)


    @Test
    fun testAssigmentWithBefore() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("a =42", defineValue(configClasses, nodeNumber))
    }

    /*@Test
    fun testAssigmentWithBeforeBoolean() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("a =true", defineValue(configClasses, nodeBoolean))
    }*/

    @Test
    fun testAssigmentWithAfter() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("a= 42", defineValue(configClasses, nodeNumber))
    }

    @Test
    fun testAssigmentWithAfterAndBefore() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("a = 42", defineValue(configClasses, nodeNumber))
    }

    @Test
    fun testAssigmentWithOut() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        Assertions.assertEquals("a=42", defineValue(configClasses, nodeNumber))
    }
}
