package lexer.languageDefinitions

class LanguageDefinitions {
    companion object {
        fun isTypeNumber(line: String, index: Int): Boolean {
            return line.length > (index + 6) && line.subSequence(index, index + 6) == "number"
        }

        fun isTypeString(line: String, index: Int): Boolean {
            return line.length > (index + 6) && line.subSequence(index, index + 6) == "string"
        }

        fun isPrintString(line: String, index: Int): Boolean {
            if (line.length > (index + 8) && line.subSequence(index, index + 8) == "println(") {
                var isOpen = false
                for (i in index + 9 until line.length) {
                    if (line[i] == '(' && !isOpen) isOpen = true
                    if (line[i] == ')') {
                        if (isOpen) {
                            isOpen = false
                        } else {
                            return true
                        }
                    }
                }
            }
            return false
        }
    }
}
