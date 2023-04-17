package strategies

import Token

class ColonStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line[index] == ':'
    }

    override fun getToken(line: String, index: Int): Token {
        return Token(PrototypeType.COLON, null)
    }
}
