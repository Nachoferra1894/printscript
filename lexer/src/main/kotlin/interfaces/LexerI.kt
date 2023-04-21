package lexer.interfaces

import Token
import kotlinx.coroutines.flow.Flow
import version.Version

interface LexerI {
    fun getTokens(codeFlow: Flow<String>, version: Version): Flow<Token>
}
