package lexer.generators

import lexer.PrototypeType
import lexer.Token

class IndexGenerator {
    companion object {
        fun defineIndex(tokens: ArrayList<Token>, index: Int): Int {
            val token: Token = tokens.last()
            if (token.prototypeType == PrototypeType.LET) return (index + 3)
            if (token.prototypeType == PrototypeType.IDENTIFIER || token.prototypeType == PrototypeType.STRING || token.prototypeType == PrototypeType.NUMBER) {
                if (token.value != null) {
                    return (index + token.value.length)
                }
            }
            if (operations(token.prototypeType)) return (index + 1)
            if (token.prototypeType == PrototypeType.STRING_TYPE ||
                token.prototypeType == PrototypeType.NUMBER_TYPE
            ) {
                return (index + 6)
            }
            return 0
        }

        private fun operations(prototypeType: PrototypeType): Boolean {
            return prototypeType == PrototypeType.OPERATOR ||
                prototypeType == PrototypeType.SUBTRACTION ||
                prototypeType == PrototypeType.ASSIGNATION ||
                prototypeType == PrototypeType.SEMICOLON ||
                prototypeType == PrototypeType.PLUS ||
                prototypeType == PrototypeType.MULTIPLICATION ||
                prototypeType == PrototypeType.DIVISION ||
                prototypeType == PrototypeType.SPACE
        }
    }
}
