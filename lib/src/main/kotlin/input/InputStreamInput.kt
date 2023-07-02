package input

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class InputStreamInput(private val inputStream: InputStream) : LexerInput {
    override fun getFlow(): Flow<String> = runBlocking {
        inputStream.asFlow()
    }
}

suspend fun InputStream.asFlow(): Flow<String> = withContext(Dispatchers.IO) {
    flow {
        BufferedReader(InputStreamReader(this@asFlow)).use { reader ->
            var line = reader.readLine()
            while (line != null) {
                emit(line)
                line = reader.readLine()
            }
        }
    }
}
