
import exceptions.NoEOLException
import exceptions.WrongTokenException
import kotlin.jvm.Throws

abstract class TokenMatcher(private val tokens: List<Token>) {
    @Throws(WrongTokenException::class, NoEOLException::class)
    fun getNextTokenOrThrowError(index: Int, tokenTypes: List<PrototypeType>): Pair<Token, Int> {
        if (index >= tokens.size) throw NoEOLException()
        val nextToken = tokens[index]
        if (nextToken.prototypeType === PrototypeType.SPACE) return getNextTokenOrThrowError(index + 1, tokenTypes)
        if (!tokenTypes.contains(nextToken.prototypeType)) throw WrongTokenException(nextToken)
        return Pair(nextToken, index + 1)
    }

    @Throws(WrongTokenException::class)
    fun getNextTokenOrThrowError(index: Int, tokenType: PrototypeType): Pair<Token, Int> {
        return getNextTokenOrThrowError(index, listOf(tokenType))
    }

    @Throws(WrongTokenException::class)
    fun getEOL(index: Int): Pair<Token, Int> {
        return getNextTokenOrThrowError(index, PrototypeType.SEMICOLON)
    }
}
