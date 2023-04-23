package subParsers

import PrototypeType
import Token
import TokenMatcher
import V1Parser
import interfaces.SubParser
import types.IfNode
import version.Version

class IfSubParser(private val tokens: List<Token>, private val version: Version) : SubParser<IfNode>, TokenMatcher(tokens) {
    val ifConditionTypes = listOf(
        PrototypeType.IDENTIFIER,
        PrototypeType.BOOLEAN
    )
    private val v1Parser = V1Parser()

    override fun getAstNode(nextIndex: Int): Pair<IfNode, Int> {
        var (ifToken, index) = getNextTokenOrThrowError(nextIndex, PrototypeType.IF)
        index = getNextTokenOrThrowError(index, PrototypeType.OPEN_PARENTHESIS).second
        val condition = getNextTokenOrThrowError(index, ifConditionTypes)
        index = condition.second
        index = getNextTokenOrThrowError(index, PrototypeType.CLOSE_PARENTHESIS).second
        index = getNextTokenOrThrowError(index, PrototypeType.OPEN_BRACE).second
        val truthySubParser = subParserController.getExpressionParser(tokens, index)
        val (truthy, truthyIndex) = truthySubParser.getAstNode(index)
        index = getNextTokenOrThrowError(truthyIndex, PrototypeType.CLOSE_BRACE).second
        index = getNextTokenOrThrowError(index, PrototypeType.ELSE).second
        index = getNextTokenOrThrowError(index, PrototypeType.OPEN_BRACE).second
        val falsySubParser = subParserController.getExpressionParser(tokens, index)
        val (falsy, falsyIndex) = falsySubParser.getAstNode(index)
        index = getNextTokenOrThrowError(falsyIndex, PrototypeType.CLOSE_BRACE).second
        return Pair(IfNode(condition.first, truthy, ifToken.line, falsy), index)
    }
}
