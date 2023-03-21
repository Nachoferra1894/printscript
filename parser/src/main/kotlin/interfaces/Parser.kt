package interfaces

interface Parser {
    fun getASTTree(tokens: List<Token>): ASTNode
}

data class Token(val a: String) // TODO delete when lexer is ready
