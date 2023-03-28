package parsers

import Token
import interfaces.Parser
import types.PrintNode

class PrintParser(tokens: List<Token>): Parser<PrintNode> {
    override fun getASTTree(): PrintNode {
        TODO("Not yet implemented")
    }
}