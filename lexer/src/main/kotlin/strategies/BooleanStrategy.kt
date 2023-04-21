package strategies

import Token
import lexer.languageDefinitions.LanguageDefinitions

class BooleanStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line[index] != '"' && LanguageDefinitions.isTypeBoolean(line, index)
    }

    override fun getToken(line: String, index: Int, lineIndex: Int): Token {
        return Token(PrototypeType.BOOLEAN_TYPE, null, index, index + 7, lineIndex)
    }
}
