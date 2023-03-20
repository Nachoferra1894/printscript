package lexer

data class Token(val prototypeType: PrototypeType, val value: String?)

enum class PrototypeType {
    ASSIGNATION,
    SEMICOLON ,
    STRING_TYPE,
    NUMBER_TYPE,
    IDENTIFIER,
    PLUS,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION,
    LET,
    NUMBER,
    STRING
}



