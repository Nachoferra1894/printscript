data class Token(val prototypeType: PrototypeType, val value: String?)

enum class PrototypeType {
    ASSIGNATION,
    SEMICOLON,
    STRING_TYPE,
    NUMBER_TYPE,
    IDENTIFIER,
    PLUS,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION,
    LET,
    NUMBER,
    STRING,
    SPACE,
    COLON,
    METHOD_PRINT,
    OPEN_PARENTHESIS,
    CLOSE_PARENTHESIS
}

val dataTypes = listOf(PrototypeType.STRING_TYPE, PrototypeType.NUMBER_TYPE)
val variableTypes = listOf(PrototypeType.STRING, PrototypeType.NUMBER)
val operatorTypes = listOf(PrototypeType.PLUS, PrototypeType.SUBTRACTION, PrototypeType.MULTIPLICATION, PrototypeType.DIVISION)
