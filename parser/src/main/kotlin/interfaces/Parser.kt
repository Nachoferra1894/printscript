package interfaces

import Token
import kotlinx.coroutines.flow.Flow
import version.Version

interface Parser {
    fun parseTokens(tokens: Flow<Token>, version: Version): ASTNode
}
