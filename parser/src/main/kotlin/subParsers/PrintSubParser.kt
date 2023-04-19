package subParsers

import Token
import TokenMatcher
import interfaces.SubParser
import types.PrintNode

class PrintSubParser(tokens: List<Token>) : SubParser<PrintNode>, TokenMatcher(tokens) {
    private val expressionSubParser = ExpressionSubParser(tokens, PrototypeType.CLOSE_PARENTHESIS)
    override fun getAstNode(nextIndex: Int): Pair<PrintNode, Int> {
        var index = getNextTokenOrThrowError(nextIndex, PrototypeType.METHOD_PRINT).second
        index = getNextTokenOrThrowError(index, PrototypeType.OPEN_PARENTHESIS).second
        val (expression, expressionIndex) = expressionSubParser.getAstNode(index)
        index = getNextTokenOrThrowError(expressionIndex, PrototypeType.SEMICOLON).second
        return Pair(PrintNode(expression, expression.getLine()), index)
    }
}
