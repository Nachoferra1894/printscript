package lexer.generators

import PrototypeType
import Token

class IndexGenerator {
    companion object {
        fun defineIndex(tokens: ArrayList<Token>, index: Int): Int {
            val token: Token = tokens.last()
            if (token.prototypeType == PrototypeType.LET) return (index + 3)
            if (token.prototypeType == PrototypeType.IDENTIFIER || token.prototypeType == PrototypeType.BOOLEAN || token.prototypeType == PrototypeType.STRING || token.prototypeType == PrototypeType.NUMBER) {
                if (token.value != null) {
                    return if (token.prototypeType == PrototypeType.STRING) {
                        (index + token.value!!.length + 2)
                    } else {
                        (index + token.value!!.length)
                    }
                }
            }
            if (operations(token.prototypeType)) return (index + 1)
            if (parenthesis(token.prototypeType)) return (index + 1)
            if (token.prototypeType == PrototypeType.STRING_TYPE ||
                token.prototypeType == PrototypeType.NUMBER_TYPE
            ) {
                return (index + 6)
            }
            if (token.prototypeType == PrototypeType.METHOD_PRINT) {
                return (index + 7)
            }
            if (token.prototypeType == PrototypeType.METHOD_READ_INPUT) {
                return (index + 9)
            }
            if (token.prototypeType == PrototypeType.IF) {
                return (index + 2)
            }
            if (token.prototypeType == PrototypeType.ELSE) {
                return (index + 4)
            }
            if (token.prototypeType == PrototypeType.BOOLEAN_TYPE) {
                return (index + 7)
            }
            if (token.prototypeType == PrototypeType.CONST) {
                return (index + 5)
            }
            return 0
        }

        private fun parenthesis(prototypeType: PrototypeType): Boolean {
            return PrototypeType.OPEN_PARENTHESIS == prototypeType ||
                PrototypeType.CLOSE_PARENTHESIS == prototypeType || PrototypeType.CLOSE_KEY == prototypeType ||
                PrototypeType.OPEN_KEY == prototypeType
        }

        private fun operations(prototypeType: PrototypeType): Boolean {
            return prototypeType == PrototypeType.COLON ||
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
