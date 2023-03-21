package types

import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class PrintExpression : ASTNode {
    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitPrint(this)
    }
}
