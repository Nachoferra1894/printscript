package subParsers

import Token
import interfaces.SubParser
import types.VariableDeclarationNode

class DeclarationSubParser(tokens: List<Token>) : SubParser<VariableDeclarationNode> {
    override fun getAstNode(nextIndex: Int): Pair<VariableDeclarationNode, Int> {
        TODO("Not yet implemented")
    }
}
