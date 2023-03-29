package subParsers

import Token
import interfaces.SubParser
import types.PrintNode

class PrintSubParser(tokens: List<Token>) : SubParser<PrintNode> {
    override fun getAstNode(nextIndex: Int): Pair<PrintNode, Int> {
        TODO("Not yet implemented")
    }
}
