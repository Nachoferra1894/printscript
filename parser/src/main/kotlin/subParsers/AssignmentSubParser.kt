package subParsers

import SubParserController
import Token
import TokenMatcher
import expresions.Expression
import interfaces.SubParser
import types.AssignmentNode
import version.Version

class AssignmentSubParser(private val tokens: List<Token>, version: Version) : SubParser<AssignmentNode>, TokenMatcher(tokens) {
    private val subParserController = SubParserController(version)

    override fun getAstNode(nextIndex: Int): Pair<AssignmentNode, Int> {
        var (variableName, index) = getNextTokenOrThrowError(nextIndex, PrototypeType.IDENTIFIER)
        index = getNextTokenOrThrowError(index, PrototypeType.ASSIGNATION).second
        val expressionSubParser = subParserController.getExpressionParser(tokens, index)
        val (expression, expressionIndex) = expressionSubParser.getAstNode(index)
        val newNode = AssignmentNode(variableName.value!!, expression as Expression, expression.getLine())
        return Pair(newNode, expressionIndex)
    }
}
