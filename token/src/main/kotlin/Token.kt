import version.V1
import version.V2
import version.Version

data class Token(val prototypeType: PrototypeType, val value: String?, val from: Int, val to: Int, val line: Int) {
    fun isEOL(): Boolean {
        return this.prototypeType === PrototypeType.SEMICOLON
    }

    fun isOpenBlock(): Boolean {
        return this.prototypeType === PrototypeType.OPEN_BRACE
    }

    fun isIf(): Boolean {
        return this.prototypeType === PrototypeType.IF
    }

    fun isCloseIf(): Boolean {
        return this.prototypeType === PrototypeType.CLOSE_PARENTHESIS
    }

    fun isCloseBlock(): Boolean {
        return this.prototypeType === PrototypeType.CLOSE_BRACE
    }

    fun isElseBlock(): Boolean {
        return this.prototypeType === PrototypeType.ELSE
    }
}

enum class PrototypeType(private val s: String) {
    EQUALS("="),
    SEMICOLON(";"),
    STRING_TYPE("string"),
    NUMBER_TYPE("number"),
    IDENTIFIER("IDENTIFIER"),
    PLUS("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    LET("let"),
    NUMBER("number"),
    STRING("string"),
    SPACE(" "),
    COLON(":"),
    METHOD_PRINT("println"),
    OPEN_PARENTHESIS("("),
    CLOSE_PARENTHESIS(")"),
    CONST("const"),
    BOOLEAN("boolean"),
    BOOLEAN_TYPE("boolean"),
    IF("if"),
    ELSE("else"),
    OPEN_BRACE("{"),
    CLOSE_BRACE("}"),
    METHOD_READ_INPUT("readInput");

    override fun toString(): String {
        return s
    }
}

fun dataTypes(version: Version): List<PrototypeType> {
    return when (version) {
        is V1 -> listOf(PrototypeType.STRING_TYPE, PrototypeType.NUMBER_TYPE)
        is V2 -> listOf(PrototypeType.STRING_TYPE, PrototypeType.NUMBER_TYPE, PrototypeType.BOOLEAN_TYPE)
    }
}

val variableTypesV1 =
    listOf(PrototypeType.STRING, PrototypeType.NUMBER, PrototypeType.IDENTIFIER)

val variableTypesV2 =
    listOf(*variableTypesV1.toTypedArray(), PrototypeType.BOOLEAN)
val operatorTypes =
    listOf(PrototypeType.PLUS, PrototypeType.SUBTRACTION, PrototypeType.MULTIPLICATION, PrototypeType.DIVISION)
