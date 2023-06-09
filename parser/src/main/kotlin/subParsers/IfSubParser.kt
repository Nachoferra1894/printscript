package subParsers

import PrototypeType
import Token
import TokenMatcher
import interfaces.SubParser
import types.IfNode
import version.Version
import java.util.*

class IfSubParser(tokens: Queue<Token>, private val version: Version) : SubParser<IfNode>, TokenMatcher(tokens) {
    private val expressionParser = ExpressionSubParser(tokens, version, PrototypeType.CLOSE_PARENTHESIS)
    private val codeParser = CodeParser(tokens, version)

    // Returns only the condition
    override fun getAstNode(): IfNode {
        getNextTokenOrThrowError(PrototypeType.IF)
        getNextTokenOrThrowError(PrototypeType.OPEN_PARENTHESIS)
        val condition = expressionParser.getAstNode()
        return IfNode(
            condition,
            condition.getLine()
        )
    }
}
