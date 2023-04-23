package subParsers

import PrototypeType
import Token
import TokenMatcher
import interfaces.SubParser
import types.IfNode
import version.Version

class IfSubParser(private val tokens: List<Token>, private val version: Version) : SubParser<IfNode>, TokenMatcher(tokens) {
    val ifConditionTypes = listOf(
        PrototypeType.IDENTIFIER,
        PrototypeType.BOOLEAN
    )
    val expressionParser = ExpressionSubParser(tokens, version, PrototypeType.CLOSE_PARENTHESIS)

    // Returns only the condition
    override fun getAstNode(nextIndex: Int): Pair<IfNode, Int> {
        var index = getNextTokenOrThrowError(nextIndex, PrototypeType.IF).second
        index = getNextTokenOrThrowError(index, PrototypeType.OPEN_PARENTHESIS).second
        val conditionExp = expressionParser.getAstNode(index)
        val condition = conditionExp.first
        index = getNextTokenOrThrowError(conditionExp.second, PrototypeType.OPEN_BRACE).second
        return Pair(IfNode(condition, condition.getLine()), index)
    }
}
