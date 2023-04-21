package strategies

import Token

class ElseStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line.length > (index + 4) && line.subSequence(index, index + 4) == "else"
    }

    override fun getToken(line: String, index: Int, lineIndex: Int): Token {
        return Token(PrototypeType.ELSE, null, index, index + 4, lineIndex)
    }
}
