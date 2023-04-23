package strategiesFormatter

import configuration.ConfigClasses
import configuration.SpaceAfterAssignation
import configuration.SpaceBeforeAssignation
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import strategiesFormatter.AssignationStrategy.Companion.defineAssignation

class AssignationStrategyTest {
    @Test
    fun testAssignationWithBefore() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals(" =", defineAssignation(configClasses))
    }

    @Test
    fun testAssignationWithAfter() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("= ", defineAssignation(configClasses))
    }

    @Test
    fun testAssignationWithAfterAndBefore() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals(" = ", defineAssignation(configClasses))
    }

    @Test
    fun testAssignationWithout() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        Assertions.assertEquals("=", defineAssignation(configClasses))
    }
}
