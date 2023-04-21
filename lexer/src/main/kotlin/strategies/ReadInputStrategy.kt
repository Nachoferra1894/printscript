package strategies

import Token
import lexer.languageDefinitions.LanguageDefinitions.Companion.isReadInputString

class ReadInputStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return isReadInputString(line, index)
    }

    override fun getToken(line: String, index: Int, lineIndex: Int): Token {
        return Token(PrototypeType.METHOD_READ_INPUT, null, index, index + 9, lineIndex)
    }
}
