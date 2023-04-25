package subParsers

import Token
import TokenMatcher
import expresions.types.ReadInputExp
import interfaces.SubParser
import version.Version
import java.util.*

class ReadInputSubParser(tokens: Queue<Token>, version: Version) : SubParser<ReadInputExp>, TokenMatcher(tokens) {
    private val expressionSubParser = ExpressionSubParser(tokens, version, PrototypeType.CLOSE_PARENTHESIS)
    override fun getAstNode(): ReadInputExp {
        getNextTokenOrThrowError(PrototypeType.METHOD_READ_INPUT)
        getNextTokenOrThrowError(PrototypeType.OPEN_PARENTHESIS)
        val expression = expressionSubParser.getAstNode()
        getEOL()
        return ReadInputExp(expression, expression.getLine())
    }
}
