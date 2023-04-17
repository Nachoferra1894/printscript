package subParsers

import Token
import TokenMatcher
import interfaces.SubParser
import types.AssignmentNode

class AssignmentSubParser(tokens: List<Token>) : SubParser<AssignmentNode>, TokenMatcher(tokens) {
    private val expressionSubParser = ExpressionSubParser(tokens)
    override fun getAstNode(nextIndex: Int): Pair<AssignmentNode, Int> {
        var (variableName,index) = getNextTokenOrThrowError(nextIndex, PrototypeType.IDENTIFIER)
        index = getNextTokenOrThrowError(index, PrototypeType.ASSIGNATION).second
        val (expression, expressionIndex) = expressionSubParser.getAstNode(index)
        val newNode = AssignmentNode(variableName.value!!, expression)
        return Pair(newNode, expressionIndex)
    }
}
