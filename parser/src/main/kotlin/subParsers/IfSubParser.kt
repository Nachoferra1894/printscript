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
    private val codeParser = CodeParser(tokens, version)

    override fun getAstNode(nextIndex: Int): Pair<IfNode, Int> {
//        var (ifToken, index) = getNextTokenOrThrowError(nextIndex, PrototypeType.IF)
//        index = getNextTokenOrThrowError(index, PrototypeType.OPEN_PARENTHESIS).second
//        val condition = getNextTokenOrThrowError(index, ifConditionTypes)
//        index = condition.second
//        index = getNextTokenOrThrowError(index, PrototypeType.CLOSE_PARENTHESIS).second
//        index = getNextTokenOrThrowError(index, PrototypeType.OPEN_BRACE).second
//
//        val truthy = codeParser.getAstNode(index)
//        val truthyNode = truthy.first
//        index = truthy.second
//
//        index = getNextTokenOrThrowError(truthyIndex, PrototypeType.CLOSE_BRACE).second
//        index = getNextTokenOrThrowError(index, PrototypeType.ELSE).second
//        index = getNextTokenOrThrowError(index, PrototypeType.OPEN_BRACE).second
//        val falsySubParser = subParserController.getExpressionParser(tokens, index)
//        val (falsy, falsyIndex) = falsySubParser.getAstNode(index)
//        index = getNextTokenOrThrowError(falsyIndex, PrototypeType.CLOSE_BRACE).second
        return Pair(IfNode(null, null, 0, null), 0)
    }
}
