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
import strategiesFormatter.DeclarationStrategy.Companion.defineValueV1
import strategiesFormatter.DeclarationStrategy.Companion.defineValueV2
import types.VariableDeclarationNode

class DeclarationStrategyTest {

    val nodeMutable = VariableDeclarationNode(
        "c",
        "number",
        Operation(Variable("3", PrototypeType.NUMBER, 1), Operator.SUM, Operation(Variable("4", PrototypeType.NUMBER, 1), Operator.MUL, Variable("5", PrototypeType.NUMBER, 1), 1), 1),
        1
    )

    val nodeNotMutable = VariableDeclarationNode(
        "c",
        "number",
        Operation(Variable("3", PrototypeType.NUMBER, 1), Operator.SUM, Operation(Variable("4", PrototypeType.NUMBER, 1), Operator.MUL, Variable("5", PrototypeType.NUMBER, 1), 1), 1),
        1,
        false
    )

    @Test
    fun testColonWithBeforeAndWithoutAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        Assertions.assertEquals("let c :number=3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndWithoutAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        Assertions.assertEquals("let c: number=3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithoutAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        Assertions.assertEquals("let c : number=3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithoutAndWithoutAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        Assertions.assertEquals("let c:number=3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithBeforeAndWithBeforeAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c :number =3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndWithBeforeAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c: number =3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithBeforeAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c : number =3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithoutAndWithBeforeAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c:number =3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithBeforeAndWithAfterAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("let c :number= 3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndWithAfterAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("let c: number= 3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithAfterAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("let c : number= 3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithoutAndWithAfterAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("let c:number= 3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithBeforeAndWithAfterAndBeforeAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c :number = 3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndWithAfterAndBeforeAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c: number = 3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithAfterAndBeforeAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c : number = 3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithoutAndWithAfterAndBeforeAssigmentV1() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c:number = 3 + 4 * 5", defineValueV1(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithBeforeAndWithoutAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        Assertions.assertEquals("let c :number=3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndWithoutAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        Assertions.assertEquals("let c: number=3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithoutAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        Assertions.assertEquals("let c : number=3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithoutAndWithoutAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        Assertions.assertEquals("let c:number=3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithBeforeAndWithBeforeAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c :number =3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndWithBeforeAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c: number =3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithBeforeAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c : number =3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithoutAndWithBeforeAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c:number =3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithBeforeAndWithAfterAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("let c :number= 3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndWithAfterAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("let c: number= 3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithAfterAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("let c : number= 3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithoutAndWithAfterAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("let c:number= 3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithBeforeAndWithAfterAndBeforeAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c :number = 3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndWithAfterAndBeforeAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c: number = 3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithAfterAndBeforeAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c : number = 3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithoutAndWithAfterAndBeforeAssigmentV2() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("let c:number = 3 + 4 * 5", defineValueV2(configClasses, nodeMutable))
    }

    @Test
    fun testColonWithBeforeAndWithoutAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        Assertions.assertEquals("const c :number=3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithAfterAndWithoutAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        Assertions.assertEquals("const c: number=3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithoutAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        Assertions.assertEquals("const c : number=3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithoutAndWithoutAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        Assertions.assertEquals("const c:number=3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithBeforeAndWithBeforeAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("const c :number =3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithAfterAndWithBeforeAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("const c: number =3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithBeforeAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("const c : number =3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithoutAndWithBeforeAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("const c:number =3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithBeforeAndWithAfterAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("const c :number= 3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithAfterAndWithAfterAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("const c: number= 3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithAfterAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("const c : number= 3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithoutAndWithAfterAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        Assertions.assertEquals("const c:number= 3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithBeforeAndWithAfterAndBeforeAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("const c :number = 3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithAfterAndWithAfterAndBeforeAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("const c: number = 3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithAfterAndBeforeAndWithAfterAndBeforeAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterColon())
        configClasses.add(SpaceBeforeColon())
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("const c : number = 3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }

    @Test
    fun testColonWithoutAndWithAfterAndBeforeAssigmentV2NotMutable() {
        val configClasses: ArrayList<ConfigClasses> = ArrayList()
        configClasses.add(SpaceAfterAssignation())
        configClasses.add(SpaceBeforeAssignation())
        Assertions.assertEquals("const c:number = 3 + 4 * 5", defineValueV2(configClasses, nodeNotMutable))
    }
}
