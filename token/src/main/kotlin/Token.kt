data class Token(val prototypeType: PrototypeType, val value: String?, val from: Int, val to: Int, val line: Int) {
    fun isEOL(): Boolean {
        return this.prototypeType === PrototypeType.SEMICOLON
    }
    fun isNextLine(): Boolean {
        return this.prototypeType === PrototypeType.SEMICOLON
    }
}

enum class PrototypeType(val s: String) {
    ASSIGNATION("="),
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
    OPEN_KEY("{"),
    CLOSE_KEY("}"),
    METHOD_READ_INPUT("readInput");

    override fun toString(): String {
        return s
    }
}

val dataTypes = listOf(PrototypeType.STRING_TYPE, PrototypeType.NUMBER_TYPE, PrototypeType.BOOLEAN_TYPE)
val variableTypes = listOf(PrototypeType.STRING, PrototypeType.NUMBER, PrototypeType.IDENTIFIER, PrototypeType.BOOLEAN)
val operatorTypes = listOf(PrototypeType.PLUS, PrototypeType.SUBTRACTION, PrototypeType.MULTIPLICATION, PrototypeType.DIVISION)
val functionTypes = listOf(PrototypeType.METHOD_PRINT, PrototypeType.METHOD_READ_INPUT)
val parenthesisTypes = listOf(PrototypeType.OPEN_PARENTHESIS, PrototypeType.CLOSE_PARENTHESIS)
val keyTypes = listOf(PrototypeType.OPEN_KEY, PrototypeType.CLOSE_KEY)
val declarationTypes = listOf(PrototypeType.LET, PrototypeType.CONST) // Maybe later we add more declaration types
