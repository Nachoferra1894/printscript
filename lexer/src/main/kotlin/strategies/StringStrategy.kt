package strategies

import Token
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeString

class StringStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line[index] != '"' && isTypeString(line, index)
    }

    override fun getToken(line: String, index: Int): Token {
        return Token(PrototypeType.STRING_TYPE, null)
    }
}
