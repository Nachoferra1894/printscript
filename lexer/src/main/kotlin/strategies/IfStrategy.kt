package strategies

import Token

class IfStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line.length > (index + 2) && line.subSequence(index, index + 2) == "if"
    }

    override fun getToken(line: String, index: Int, lineIndex: Int): Token {
        return Token(PrototypeType.IF, null, index, index + 2, lineIndex)
    }
}
