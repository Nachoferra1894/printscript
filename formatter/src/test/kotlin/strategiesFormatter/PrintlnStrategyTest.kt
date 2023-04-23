package strategiesFormatter

import configuration.ConfigClasses
import configuration.LineBrakeForPrintln
import expresions.types.Variable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import strategiesFormatter.PrintlnStrategy.Companion.defineValue
import types.PrintNode

class PrintlnStrategyTest {
    val node = PrintNode(Variable("Hello, world!", PrototypeType.STRING, 1), 1)

    @Test
    fun testPrintlnWith0() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add((LineBrakeForPrintln(0)))
        Assertions.assertEquals("println(\"Hello, world!\")", defineValue(configClasses, node))
    }

    @Test
    fun testPrintlnWith1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add((LineBrakeForPrintln(1)))
        Assertions.assertEquals("\nprintln(\"Hello, world!\")", defineValue(configClasses, node))
    }

    @Test
    fun testPrintlnWith2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add((LineBrakeForPrintln(2)))
        Assertions.assertEquals("\n\nprintln(\"Hello, world!\")", defineValue(configClasses, node))
    }

    @Test
    fun testPrintlnWith3() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add((LineBrakeForPrintln(3)))
        Assertions.assertEquals("\n\nprintln(\"Hello, world!\")", defineValue(configClasses, node))
    }
}
