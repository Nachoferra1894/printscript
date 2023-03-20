package lexer.generators

import lexer.PrototypeType
import lexer.Token
import lexer.strategies.TokenStrategy
import lexer.exceptions.NoTokenException

class TokenGenerator {
    companion object {
         fun getIdentifierToken( line: String , index: Int) : Token {
            val value = ""
            while (index < line.length || line[index] == ':')
                value.plus(line[index])
             index.plus(1)
            return Token(PrototypeType.IDENTIFIER, value)
        }
         fun getLetToken() : Token {
            return Token(PrototypeType.LET, null)
        }
         fun getValueToken(line: String, index: Int) : Token {
            if(line[index].isDigit()) return valueNumber(line, index)
            if(line[index] == '"') return valueString(line, index)
            throw NoTokenException("No value exists with this operator " + line[index])
        }

        private fun valueNumber(line: String, index: Int): Token {
            var number: String = line[index].toString()
            var actualIndex : Int = index
            while (actualIndex < line.length && line[index] != ' ' && !TokenStrategy.finalStrategy(line, index)) {
                var also = number.plus(line[index].toString()).also { number = it }
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
            if (!isClosed)
                NoTokenException("No value exists with this operator " + line[index])
            return Token(PrototypeType.STRING, value)
        }
         fun getTypeStrategy(line: String, index: Int ) : Token {
            return if(line.subSequence(index, index + 5) == "String") Token(PrototypeType.STRING_TYPE, null)
            else Token(PrototypeType.NUMBER_TYPE, null)
        }
         fun getOperationStrategy(line: String, index: Int) : Token {
            when(line[index]){
                '=' -> return Token(PrototypeType.ASSIGNATION, null)
                '.' -> return Token(PrototypeType.MULTIPLICATION, null)
                '/' -> return Token(PrototypeType.DIVISION, null)
                '+' -> return Token(PrototypeType.PLUS, null)
                '-' -> return Token(PrototypeType.SUBTRACTION, null)
            }
            throw NoTokenException("No operation exists with this operator " + line[index])
        }
         fun getFinalToken() : Token {
            return Token(PrototypeType.SEMICOLON, null)
        }
    }
}