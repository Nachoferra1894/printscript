package strategiesFormatter

import configuration.ConfigClasses
import configuration.SpaceAfterColon
import configuration.SpaceBeforeColon
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import strategiesFormatter.ColonStrategy.Companion.defineColon

class ColonStrategyTest {

    @Test
    fun testColonWithBefore() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        assertEquals(" :", defineColon(configClasses))
    }

    @Test
    fun testColonWithAfter() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        assertEquals(": ", defineColon(configClasses))
    }

    @Test
    fun testColonWithBeforeAndAfter() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        assertEquals(" : ", defineColon(configClasses))
    }

    @Test
    fun testColonWithout() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        assertEquals(":", defineColon(configClasses))
    }
}
