package strategies

import Token
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeNumber

class NumberStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line[index] != '"' && isTypeNumber(line, index)
    }

    override fun getToken(line: String, index: Int, lineIndex: Int): Token {
        return Token(PrototypeType.NUMBER_TYPE, null, index, index + 6, lineIndex)
    }
}
