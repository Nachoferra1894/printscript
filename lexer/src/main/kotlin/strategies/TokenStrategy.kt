package strategies

class TokenStrategy {
    companion object {
        fun letStrategy(line: String, index: Int): Boolean {
            return line.length > (index + 3) && line.subSequence(index, index + 3) == "let"
        }
        fun constStrategy(line: String, index: Int): Boolean {
            return line.length > (index + 4) && line.subSequence(index, index + 4) == "const"
        }
        fun spaceStrategy(line: String, index: Int): Boolean {
            return line[index] == ' '
        }

        fun finalStrategy(line: String, index: Int): Boolean {
            return line[index] == ';'
        }

        fun operationStrategy(line: String, index: Int): Boolean {
            val pattern = Regex("=|-|/|\\+|\\*|:|")
            return pattern.matches(line[index].toString())
        }

        fun parenthesisStrategy(line: String, index: Int): Boolean {
            return line[index] == '(' || line[index] == ')'
        }
    }
}
