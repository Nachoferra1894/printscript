package strategies

import Token
import lexer.exceptions.NoTokenException

class ValueStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line[index] == '"' || line[index].isDigit()
    }

    override fun getToken(line: String, index: Int, lineIndex: Int): Token {
        if (line[index].isDigit()) return valueNumber(line, index, lineIndex)
        if (line[index] == '"') return valueString(line, index, lineIndex)
        throw NoTokenException("No value exists with this operator " + line[index])
    }

    private fun valueNumber(line: String, index: Int, lineIndex: Int): Token {
        var number: String = line[index].toString()
        var actualIndex: Int = index + 1
        while (actualIndex < line.length - 1 && line[actualIndex] != ' ' || line[actualIndex] != ';' && !TokenStrategy.finalStrategy(line, index)) {
            number.plus(line[actualIndex].toString()).also { number = it }
            actualIndex = actualIndex.plus(1)
        }
        return Token(PrototypeType.NUMBER, number, index, index + number.length, lineIndex)
    }

    private fun valueString(line: String, index: Int, lineIndex: Int): Token {
        var value = ""
        var isClosed = false
        for (i in index + 1 until line.length) {
            if (line[i] == '"') isClosed = true
            if (isClosed) break
            if (TokenStrategy.finalStrategy(line, i) && !isClosed) {
                throw NoTokenException("No value exists with this operator " + line[i])
            }
            if (TokenStrategy.finalStrategy(line, i)) {
                return Token(PrototypeType.STRING, value, index, index + value.length, lineIndex)
            }
            value = value.plus(line[i])
        }
        return Token(PrototypeType.STRING, value, index, index + value.length, lineIndex)
    }
}
