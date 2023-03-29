package subParsers

import Token
import TokenMatcher
import interfaces.SubParser
import types.AssignmentNode

class AssignmentSubParser(tokens: List<Token>) : SubParser<AssignmentNode>, TokenMatcher(tokens) {
    override fun getAstNode(nextIndex: Int): Pair<AssignmentNode, Int> {
        val variableName = getNextTokenOrThrowError(nextIndex, PrototypeType.IDENTIFIER)
        val equals = getNextTokenOrThrowError(nextIndex, PrototypeType.ASSIGNATION)
        return TODO()
    }
}
