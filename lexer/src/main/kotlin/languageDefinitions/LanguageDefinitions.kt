package lexer.languageDefinitions

class LanguageDefinitions {
    companion object {
        fun isTypeNumber(line: String, index: Int): Boolean {
            return line.length > (index + 6) && line.subSequence(index, index + 6) == "number"
        }

        fun isTypeString(line: String, index: Int): Boolean {
            return line.length > (index + 6) && line.subSequence(index, index + 6) == "string"
        }
        fun isTypeBoolean(line: String, index: Int): Boolean {
            return line.length > (index + 7) && line.subSequence(index, index + 7) == "boolean"
        }

        fun isTypeBoolean(line: String, index: Int): Boolean {
            return line.length > (index + 7) && line.subSequence(index, index + 7) == "boolean"
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

        fun isReadInputString(line: String, index: Int): Boolean {
            if (line.length > (index + 10) && line.subSequence(index, index + 10) == "readInput(") {
                var isOpen = false
                for (i in index + 11 until line.length) {
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
