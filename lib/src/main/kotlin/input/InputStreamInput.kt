package input

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.InputStream

class InputStreamInput(private val inputStream: InputStream) : LexerInput {
    override fun getFlow(): Flow<String> {
        return flow {
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach { line ->
                    emit(line)
                }
            }
        }
    }
}
