package strategies

import Token
import lexer.exceptions.NoTokenException

class ValueStrategy : Strategy {
    override fun isStrategy(line: String, index: Int): Boolean {
        return line[index] == '"' || line[index].isDigit() || isTrue(line, index) || isFalse(line, index)
    }

    override fun getToken(line: String, index: Int, lineIndex: Int): Token {
        if (line[index].isDigit()) return valueNumber(line, index, lineIndex)
        if (line[index] == '"') return valueString(line, index, lineIndex)
        if (isTrue(line, index)) return valueTrue(index, lineIndex)
        if (isFalse(line, index)) return valueFalse(index, lineIndex)
        throw NoTokenException("No value exists with this operator " + line[index])
    }
    private fun isTrue(line: String, index: Int): Boolean {
        return line.length > (index + 4) && line.subSequence(index, index + 4) == "true"
    }

    private fun isFalse(line: String, index: Int): Boolean {
        return line.length > (index + 5) && line.subSequence(index, index + 5) == "false"
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

    private fun valueTrue(index: Int, lineIndex: Int): Token {
        return Token(PrototypeType.BOOLEAN, "true", index, index + 4, lineIndex)
    }

    private fun valueFalse(index: Int, lineIndex: Int): Token {
        return Token(PrototypeType.BOOLEAN, "false", index, index + 5, lineIndex)
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
