package strategies

import Token

class LetStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        println("ENTRE A LET STRATEGY")
        println(line.length > (index + 3) && line.subSequence(index, index + 3) == "let")
        return line.length > (index + 3) && line.subSequence(index, index + 3) == "let"
    }

    override fun getToken(line: String, index: Int): Token {
        return Token(PrototypeType.LET, null)
    }
}
