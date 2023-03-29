import exceptions.WrongTokenException
import kotlin.jvm.Throws

abstract class TokenMatcher(private val tokens: List<Token>) {
    @Throws(WrongTokenException::class)
    fun getNextTokenOrThrowError(index: Int, tokenTypes: List<PrototypeType>): Token {
        val nextToken = tokens[index]
        if (nextToken.prototypeType === PrototypeType.SPACE) return getNextTokenOrThrowError(index + 1, tokenTypes)
        if (!tokenTypes.contains(nextToken.prototypeType)) throw WrongTokenException(nextToken)
        return nextToken
    }

    @Throws(WrongTokenException::class)
    fun getNextTokenOrThrowError(index: Int, tokenType: PrototypeType): Token {
        val nextToken = tokens[index]
        if (nextToken.prototypeType === PrototypeType.SPACE) return getNextTokenOrThrowError(index + 1, tokenType)
        if (tokenType !== nextToken.prototypeType) throw WrongTokenException(nextToken)
        return nextToken
    }
}
