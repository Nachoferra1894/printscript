package interfaces

import Token
import kotlinx.coroutines.flow.Flow

interface Parser {
    fun parseTokens(tokens: Flow<Token>): ASTNode
}
