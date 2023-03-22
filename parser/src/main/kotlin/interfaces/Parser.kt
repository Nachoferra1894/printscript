package interfaces

import Token

interface Parser {
    fun getASTTree(tokens: List<Token>): ASTNode
}

