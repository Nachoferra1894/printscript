package lexer.strategies
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeNumber
import lexer.languageDefinitions.LanguageDefinitions.Companion.isTypeString

class TokenStrategy {
    companion object {
        fun letStrategy(line: String, index: Int ) : Boolean {
            return line.length > (index + 3) && line.subSequence(index, index + 3 ) == "let"
        }
        fun spaceStrategy(line: String, index: Int ) : Boolean {
            return line[index] == ' '
        }
        fun identifierStrategy(line: String, index: Int ) : Boolean {
            var lastIndex : Int = index
            while (lastIndex < line.length  && line[lastIndex] != ' '){
                lastIndex = lastIndex.plus(1)
            }
            return line[lastIndex - 1] == ':'
        }
        fun valueStrategy(line:String, index: Int) : Boolean {
            return line[index] == '"' || line[index].isDigit()
        }
        fun finalStrategy(line: String, index: Int ) : Boolean {
            return line[index] == ';'
        }

        fun operationStrategy(line: String, index: Int) : Boolean {
            val pattern = Regex("=|-|/|\\+|\\.|:|")
            return pattern.matches(line[index].toString())
        }

        fun typeStrategy(line: String, index: Int ) : Boolean {
            return line[index] != '"' && (isTypeString(line, index) ||
                    isTypeNumber(line, index))
        }

    }

}