
import exceptions.NoEOLException
import exceptions.WrongTokenException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import java.util.Queue
import kotlin.jvm.Throws

abstract class TokenMatcher(val tokens: Queue<Token>) {
    @Throws(WrongTokenException::class, NoEOLException::class)
    fun getNextTokenOrThrowError(tokenTypes: List<PrototypeType>): Token = runBlocking {
        val firstToken = tokens.peek() ?: throw NoEOLException()
        if (firstToken.prototypeType === PrototypeType.SPACE) {
            tokens.poll()
            getNextTokenOrThrowError(tokenTypes)
        }
        if (!tokenTypes.contains(firstToken.prototypeType)) throw WrongTokenException(firstToken)
        tokens.poll()
        firstToken
    }

    @Throws(WrongTokenException::class)
    fun getNextTokenOrThrowError(tokenType: PrototypeType): Token {
        return getNextTokenOrThrowError(listOf(tokenType))
    }

    @Throws(WrongTokenException::class)
    fun getEOL(): Token {
        return getNextTokenOrThrowError(PrototypeType.SEMICOLON)
    }
}
