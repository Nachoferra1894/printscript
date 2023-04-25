package subParsers

import Token
import TokenMatcher
import interfaces.SubParser
import types.PrintNode
import version.Version
import java.util.*

class PrintSubParser(tokens: Queue<Token>, version: Version) : SubParser<PrintNode>, TokenMatcher(tokens) {
    private val expressionSubParser = ExpressionSubParser(tokens, version, PrototypeType.CLOSE_PARENTHESIS)
    override fun getAstNode(): PrintNode {
        getNextTokenOrThrowError(PrototypeType.METHOD_PRINT)
        getNextTokenOrThrowError(PrototypeType.OPEN_PARENTHESIS)
        val expression = expressionSubParser.getAstNode()
        getEOL()
        return PrintNode(expression, expression.getLine())
    }
}
