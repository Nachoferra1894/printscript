package input

import kotlinx.coroutines.flow.Flow

interface LexerInput {
    fun getFlow(): Flow<String>
}
