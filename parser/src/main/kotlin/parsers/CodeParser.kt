package parsers

import Token
import interfaces.Parser
import types.ParentNode

class CodeParser(tokens: List<Token>) : Parser<ParentNode> {
    override fun getASTTree(): ParentNode {
        TODO("Not yet implemented")
    }
}
