package strategies

import lexer.languageDefinitions.LanguageDefinitions.Companion.isPrintString
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeBoolean
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeNumber
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeString

class TokenStrategy {
    companion object {
        fun letStrategy(line: String, index: Int): Boolean {
            return line.length > (index + 3) && line.subSequence(index, index + 3) == "let"
        }
        fun constStrategy(line: String, index: Int): Boolean {
            return line.length > (index + 4) && line.subSequence(index, index + 4) == "const"
        }
        fun spaceStrategy(line: String, index: Int): Boolean {
            return line[index] == ' '
        }
        fun identifierStrategy(line: String, index: Int): Boolean {
            return line[index].isLetter()
        }

        fun valueStrategy(line: String, index: Int): Boolean {
            return line[index] == '"' || line[index].isDigit()
        }
        fun finalStrategy(line: String, index: Int): Boolean {
            return line[index] == ';'
        }

        fun operationStrategy(line: String, index: Int): Boolean {
            val pattern = Regex("=|-|/|\\+|\\*|:|")
            return pattern.matches(line[index].toString())
        }

        fun typeStrategy(line: String, index: Int): Boolean {
            return line[index] != '"' && (
                isTypeString(line, index) ||
                    isTypeNumber(line, index) || isTypeBoolean(line, index)
                )
        }
        fun printStrategy(line: String, index: Int): Boolean {
            return isPrintString(line, index)
        }

        fun parenthesisStrategy(line: String, index: Int): Boolean {
            return line[index] == '(' || line[index] == ')'
        }
    }
}
