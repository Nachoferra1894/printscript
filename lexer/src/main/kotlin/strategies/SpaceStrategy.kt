package strategies

import Token

class SpaceStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line[index] == ' '
    }

    override fun getToken(line: String, index: Int, lineIndex: Int): Token {
        return Token(PrototypeType.SPACE, null, index, index + 1,lineIndex)
    }
}
