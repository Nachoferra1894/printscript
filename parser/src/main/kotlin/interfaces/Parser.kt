package interfaces

import Token

interface Parser {
    fun parseTokens(tokens: List<Token>): ASTNode
}
