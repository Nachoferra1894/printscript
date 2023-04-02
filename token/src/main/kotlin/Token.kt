data class Token(val prototypeType: PrototypeType, val value: String?) {
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
    IDENTIFIER("="),
    PLUS("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    LET("let"),
    NUMBER("number"),
    STRING("string"),
    SPACE(" "),
    COLON(":"),
    METHOD_PRINT("print"),
    OPEN_PARENTHESIS("("),
    CLOSE_PARENTHESIS(")");

    override fun toString(): String {
        return s
    }
}

val dataTypes = listOf(PrototypeType.STRING_TYPE, PrototypeType.NUMBER_TYPE)
val variableTypes = listOf(PrototypeType.STRING, PrototypeType.NUMBER)
val operatorTypes = listOf(PrototypeType.PLUS, PrototypeType.SUBTRACTION, PrototypeType.MULTIPLICATION, PrototypeType.DIVISION)
val functionTypes = listOf(PrototypeType.METHOD_PRINT)
val parenthesisTypes = listOf(PrototypeType.OPEN_PARENTHESIS, PrototypeType.CLOSE_PARENTHESIS)
val declarationTypes = listOf(PrototypeType.LET) // Maybe later we add more declaration types
