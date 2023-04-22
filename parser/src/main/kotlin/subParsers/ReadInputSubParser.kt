package subParsers

import Token
import TokenMatcher
import expresions.types.ReadInputExp
import interfaces.SubParser
import version.Version

class ReadInputSubParser(tokens: List<Token>, private val version: Version) : SubParser<ReadInputExp>, TokenMatcher(tokens) {
    private val expressionSubParser = ExpressionSubParser(tokens, version, PrototypeType.CLOSE_PARENTHESIS)
    override fun getAstNode(nextIndex: Int): Pair<ReadInputExp, Int> {
        var index: Int = getNextTokenOrThrowError(nextIndex, PrototypeType.METHOD_READ_INPUT).second
        index = getNextTokenOrThrowError(index, PrototypeType.OPEN_PARENTHESIS).second
        val (expression, expressionIndex) = expressionSubParser.getAstNode(index)
        return Pair(ReadInputExp(expression, expression.getLine()), expressionIndex)
    }
}
