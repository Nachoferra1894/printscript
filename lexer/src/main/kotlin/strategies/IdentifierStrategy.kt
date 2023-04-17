package strategies

import Token

class IdentifierStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line[index].isLetter()
    }

    override fun getToken(line: String, index: Int): Token {
        var value = ""
        for (i in index until line.length) {
            if (TokenStrategy.operationStrategy(line, i) || TokenStrategy.spaceStrategy(line, i) || TokenStrategy.parenthesisStrategy(line, i) || TokenStrategy.finalStrategy(line, i)) {
                return Token(PrototypeType.IDENTIFIER, value)
            }
            value = value.plus(line[i])
        }
        return Token(PrototypeType.IDENTIFIER, value)
    }
}