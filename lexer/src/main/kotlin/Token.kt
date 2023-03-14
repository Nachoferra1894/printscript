package lexer

data class Token (val prototypeType: PrototypeType, val value : String )

enum class PrototypeType {
    ASSIGNATION, SEMICOLON , STRING_TYPE, NUMBER, IDENTIFIER, PLUS, SUBTRACTION, MULTIPLICATION, DIVISION, NUMBER_TYPE, STRING
}



