package lexer.interfaces

import Token
import input.LexerInput
import kotlinx.coroutines.flow.Flow

interface LexerI {
    fun getTokens(codeFlow: Flow<String>): Flow<Token>
}
