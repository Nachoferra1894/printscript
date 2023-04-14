package input

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

class LexerFileInput (private val file: File): LexerInput{
    override fun getFlow(): Flow<String> {
        val text = file.readText()
        val lines = text.split("\n")
        return flow {
            lines.forEach { line ->
                emit(line)
            }
        }
    }
}
