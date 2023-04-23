package strategiesFormatter

import configuration.ConfigClasses
import configuration.SpaceAfterAssignation
import configuration.SpaceAfterColon
import configuration.SpaceBeforeAssignation
import configuration.SpaceBeforeColon
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import strategiesFormatter.DeclarationStrategy.Companion.defineValue
import types.VariableDeclarationNode

class DeclarationStrategyTest {

    val node = VariableDeclarationNode(
        "c",
        "number",
        Operation(Variable("3", PrototypeType.NUMBER, 1), Operator.SUM, Operation(Variable("4", PrototypeType.NUMBER, 1), Operator.MUL, Variable("5", PrototypeType.NUMBER, 1), 1), 1),
        1
    )

    @Test
    fun testColonWithBeforeAndWithoutAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        Assertions.assertEquals("let c :number=3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithAfterAndWithoutAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        Assertions.assertEquals("let c: number=3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithoutAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        Assertions.assertEquals("let c : number=3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithoutAndWithoutAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        Assertions.assertEquals("let c:number=3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithBeforeAndWithBeforeAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c :number =3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithAfterAndWithBeforeAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c: number =3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithBeforeAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c : number =3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithoutAndWithBefoeAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c:number =3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithBeforeAndWithAfterAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("let c :number= 3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithAfterAndWithAfterAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("let c: number= 3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithAfterAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("let c : number= 3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithoutAndWithAfterAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("let c:number= 3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithBeforeAndWithAfterAndBeforeAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c :number = 3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithAfterAndWithAfterAndBeforeAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c: number = 3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithAfterAndBeforeAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c : number = 3 + 4 * 5", defineValue(configClasses, node))
    }

    @Test
    fun testColonWithoutAndWithAfterAndBeforeAssigment() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c:number = 3 + 4 * 5", defineValue(configClasses, node))
    }
}
