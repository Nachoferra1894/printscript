package parsers

import Token
import interfaces.Parser
import types.VariableDeclarationNode

class DeclarationParser(tokens: List<Token>): Parser<VariableDeclarationNode> {
    override fun getASTTree(): VariableDeclarationNode {
        TODO("Not yet implemented")
    }
}