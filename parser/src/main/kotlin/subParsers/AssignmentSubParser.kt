package subParsers

import Token
import TokenMatcher
import interfaces.SubParser
import types.AssignmentNode
import version.Version

class AssignmentSubParser(tokens: List<Token>, version: Version) : SubParser<AssignmentNode>, TokenMatcher(tokens) {
    private val expressionSubParser = ExpressionSubParser(tokens, version)
    override fun getAstNode(nextIndex: Int): Pair<AssignmentNode, Int> {
        var (variableName, index) = getNextTokenOrThrowError(nextIndex, PrototypeType.IDENTIFIER)
        index = getNextTokenOrThrowError(index, PrototypeType.ASSIGNATION).second
        val (expression, expressionIndex) = expressionSubParser.getAstNode(index)
        val newNode = AssignmentNode(variableName.value!!, expression, expression.getLine())
        return Pair(newNode, expressionIndex)
    }
}
