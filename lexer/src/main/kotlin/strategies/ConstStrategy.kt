package strategies

import Token

class ConstStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line.length > (index + 5) && line.subSequence(index, index + 5) == "const"
    }

    override fun getToken(line: String, index: Int, lineIndex: Int): Token {
        return Token(PrototypeType.CONST, null, index, index + 5, lineIndex)
    }
}
