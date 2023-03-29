package subParsers

import Token
import interfaces.SubParser
import types.ParentNode

class CodeSubParser(tokens: List<Token>) : SubParser<ParentNode> {
    override fun getAstNode(nextIndex: Int): Pair<ParentNode, Int> {
        TODO("Not yet implemented")
    }
}
