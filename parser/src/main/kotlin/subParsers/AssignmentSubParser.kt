package subParsers

import Token
import TokenMatcher
import interfaces.SubParser
import types.AssignmentNode

class AssignmentSubParser(tokens: List<Token>) : SubParser<AssignmentNode>, TokenMatcher(tokens) {
    private val expressionSubParser = ExpressionSubParser(tokens)
    override fun getAstNode(nextIndex: Int): Pair<AssignmentNode, Int> {
        var index = nextIndex
        val variableName = getNextTokenOrThrowError(index, PrototypeType.IDENTIFIER)
        index++
        val equals = getNextTokenOrThrowError(index, PrototypeType.ASSIGNATION)
        index++
        val (expression, expressionIndex) = expressionSubParser.getAstNode(index)
        val newNode = AssignmentNode(variableName.value!!, expression)
        return Pair(newNode, expressionIndex)
    }
}
