package strategies

import lexer.languageDefinitions.LanguageDefinitions.Companion.isPrintString
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeNumber
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeString

class TokenStrategy {
    companion object {
        fun spaceStrategy(line: String, index: Int): Boolean {
            return line[index] == ' '
        }

        fun finalStrategy(line: String, index: Int): Boolean {
            return line[index] == ';'
        }

        fun operationStrategy(line: String, index: Int): Boolean {
            val pattern = Regex("=|-|/|\\+|\\*|:|")
            return pattern.matches(line[index].toString())
        }


        fun parenthesisStrategy(line: String, index: Int): Boolean {
            return line[index] == '(' || line[index] == ')'
        }
    }
}
