package lexer.languageDefinitions

class LanguageDefinitions {
    companion object {
        fun isTypeNumber(line: String, index: Int): Boolean {
            return checkTypeSubsequence(line, index, 6, "number")
        }

        fun isTypeString(line: String, index: Int): Boolean {
            return checkTypeSubsequence(line, index, 6, "string")
        }

        fun isTypeBoolean(line: String, index: Int): Boolean {
            return checkTypeSubsequence(line, index, 7, "boolean")
        }

        private fun checkTypeSubsequence(line: String, index: Int, maxIndex: Int, type: String): Boolean {
            val max = index + maxIndex
            return line.length > (index + maxIndex) && line.subSequence(
                index,
                max
            ) == type && line.length > max && !line.subSequence(max, max + 1)[0].isLetter()
            // Because it should always finish in a ; and if the next char is a letter, means is a variable
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
