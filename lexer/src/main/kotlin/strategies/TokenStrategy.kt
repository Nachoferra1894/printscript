package lexer.strategies

import PrototypeType
import Token
import lexer.exceptions.NoTokenException
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeNumber
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeString

class TokenStrategy {
    companion object {
        fun letStrategy(line: String, index: Int): Boolean {
            return line.length > (index + 3) && line.subSequence(index, index + 3) == "let"
        }
        fun spaceStrategy(line: String, index: Int): Boolean {
            return line[index] == ' '
        }
        fun identifierStrategy(line: String, index: Int): Boolean {
            if (line[index].isLetter()) {
                var lastIndex: Int = index
                for (i in index until line.length) {
                    if (line[lastIndex] == ' ' || line[lastIndex] == ':') return true
                    if (!line[lastIndex].isDigit() && !line[lastIndex].isLetter())
                        throw NoTokenException(
                            "No token with this expression " + line.subSequence(
                                index,
                                lastIndex + 1
                            )
                        )
                    lastIndex = lastIndex.plus(1)
                }
                return true
            } else {
                return false
            }
        }

        fun valueStrategy(line: String, index: Int): Boolean {
            return line[index] == '"' || line[index].isDigit()
        }
        fun finalStrategy(line: String, index: Int): Boolean {
            return line[index] == ';'
        }

        fun operationStrategy(line: String, index: Int): Boolean {
            val pattern = Regex("=|-|/|\\+|\\.|:|")
            return pattern.matches(line[index].toString())
        }

        fun typeStrategy(line: String, index: Int): Boolean {
            return line[index] != '"' && (
                isTypeString(line, index) ||
                    isTypeNumber(line, index)
                )
        }
    }
}
