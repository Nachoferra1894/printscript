package strategies

import Token

class MultiplicationStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line[index] == '*'
    }

    override fun getToken(line: String, index: Int): Token {
        return Token(PrototypeType.MULTIPLICATION, null)
    }
}
