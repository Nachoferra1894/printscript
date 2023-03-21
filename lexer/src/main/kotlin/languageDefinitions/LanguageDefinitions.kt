package lexer.languageDefinitions

class LanguageDefinitions {
    companion object {
        fun isTypeNumber(line: String, index: Int) = line.subSequence(index, index + 6) == "number"

        fun isTypeString(line: String, index: Int) = line.subSequence(index, index + 6) == "string"
    }
}