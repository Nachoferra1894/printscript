package subParsers

import Token
import TokenMatcher
import interfaces.SubParser
import types.PrintNode

class PrintSubParser(tokens: List<Token>) : SubParser<PrintNode>, TokenMatcher(tokens) {
    private val expressionSubParser = ExpressionSubParser(tokens, PrototypeType.CLOSE_PARENTHESIS)
    override fun getAstNode(nextIndex: Int): Pair<PrintNode, Int> {
        var index = nextIndex
        getNextTokenOrThrowError(index, PrototypeType.METHOD_PRINT)
        index++
        getNextTokenOrThrowError(index, PrototypeType.OPEN_PARENTHESIS)
        index++
        val (expression, expressionIndex) = expressionSubParser.getAstNode(index)
        return Pair(PrintNode(expression), expressionIndex)
    }
}
