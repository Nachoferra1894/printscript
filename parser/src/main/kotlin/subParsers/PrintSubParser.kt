package subParsers

import Token
import TokenMatcher
import interfaces.SubParser
import types.PrintNode

class PrintSubParser(tokens: List<Token>) : SubParser<PrintNode>, TokenMatcher(tokens) {
    private val expressionSubParser = ExpressionSubParser(tokens)
    override fun getAstNode(nextIndex: Int): Pair<PrintNode, Int> {
        getNextTokenOrThrowError(nextIndex, PrototypeType.METHOD_PRINT)
        val (expression, expressionIndex) = expressionSubParser.getAstNode(nextIndex+1)
        return Pair(PrintNode(expression), expressionIndex)
    }
}
