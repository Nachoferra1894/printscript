package strategies

import Token

class ParenthesisStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line[index] == '(' || line[index] == ')'
    }

    override fun getToken(line: String, index: Int, lineIndex: Int): Token {
        return if (line[index] == ')') {
            Token(PrototypeType.CLOSE_PARENTHESIS, null, index, index + 1, lineIndex)
        } else {
            Token(PrototypeType.OPEN_PARENTHESIS, null, index, index + 1, lineIndex)
        }
    }
}
