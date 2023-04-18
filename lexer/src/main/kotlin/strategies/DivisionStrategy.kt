package strategies

import Token

class DivisionStrategy : Strategy {

    override fun isStrategy(line: String, index: Int): Boolean {
        return line[index] == '/'
    }

    override fun getToken(line: String, index: Int, lineIndex: Int): Token {
        return Token(PrototypeType.DIVISION, null, index, index + 1, lineIndex)
    }
}
