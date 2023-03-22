package lexer.generators

import PrototypeType
import Token
import lexer.exceptions.NoTokenException
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeNumber
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeString
import lexer.strategies.TokenStrategy

class TokenGenerator {
    companion object {
        fun getIdentifierToken(line: String, index: Int): Token {
            var value = ""
            var actualIndex: Int = index

            while (line[actualIndex] != ':' && actualIndex < line.length - 1) {
                value.plus(line[actualIndex]).also { value = it }
                actualIndex.plus(1).also { actualIndex = it }
            }
            return Token(PrototypeType.IDENTIFIER, value)
        }
        fun getLetToken(): Token {
            return Token(PrototypeType.LET, null)
        }
        fun getValueToken(line: String, index: Int): Token {
            if (line[index].isDigit()) return valueNumber(line, index)
            if (line[index] == '"') return valueString(line, index)
            throw NoTokenException("No value exists with this operator " + line[index])
        }

        private fun valueNumber(line: String, index: Int): Token {
            var number: String = line[index].toString()
            var actualIndex: Int = index + 1
            while (actualIndex < line.length - 1 && line[actualIndex] != ' ' || line[actualIndex] != ';' && !TokenStrategy.finalStrategy(line, index)) {
                number.plus(line[actualIndex].toString()).also { number = it }
                actualIndex = actualIndex.plus(1)
            }
            return Token(PrototypeType.NUMBER, number)
        }

        private fun valueString(line: String, index: Int): Token {
            var value = ""
            var isClosed = false

            while (index < line.length && !TokenStrategy.finalStrategy(line, index) && !isClosed) {
                if (line[index] == '"') isClosed = true
                value.plus(line[index]).also { value = it }
            }
            if (!isClosed) {
                throw NoTokenException("No value exists with this operator " + line[index])
            }
            return Token(PrototypeType.STRING, value)
        }
        fun getTypeStrategy(line: String, index: Int): Token {
            if (isTypeString(line, index)) return Token(PrototypeType.STRING_TYPE, null)
            if (isTypeNumber(line, index)) return Token(PrototypeType.NUMBER_TYPE, null)
            throw NoTokenException("No type exists with this syntax")
        }
        fun getSpaceToken(): Token {
            return Token(PrototypeType.SPACE, null)
        }
        fun getOperationStrategy(line: String, index: Int): Token {
            when (line[index]) {
                '=' -> return Token(PrototypeType.ASSIGNATION, null)
                '.' -> return Token(PrototypeType.MULTIPLICATION, null)
                '/' -> return Token(PrototypeType.DIVISION, null)
                '+' -> return Token(PrototypeType.PLUS, null)
                '-' -> return Token(PrototypeType.SUBTRACTION, null)
                ':' -> return Token(PrototypeType.OPERATOR, null)
            }
            throw NoTokenException("No operation exists with this operator " + line[index])
        }
        fun getFinalToken(): Token {
            return Token(PrototypeType.SEMICOLON, null)
        }
    }
}
