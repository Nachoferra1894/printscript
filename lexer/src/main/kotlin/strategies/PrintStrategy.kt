package strategies

import Token
import lexer.languageDefinitions.LanguageDefinitions.Companion.isPrintString

class PrintStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return isPrintString(line, index)
    }

    override fun getToken(line: String, index: Int, lineIndex: Int): Token {
        return Token(PrototypeType.METHOD_PRINT, null, index, index + 7, lineIndex)
    }
}
