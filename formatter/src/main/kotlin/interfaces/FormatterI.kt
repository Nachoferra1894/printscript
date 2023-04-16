package interfaces

interface FormatterI {
    fun getFormattedCode(node: ASTNode): String
}
