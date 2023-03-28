package parsers

import Token
import interfaces.Parser
import types.AssignmentNode

class AssignmentParser(tokens: List<Token>) : Parser<AssignmentNode> {
    override fun getASTTree(): AssignmentNode {
        TODO("Not yet implemented")
    }
}
