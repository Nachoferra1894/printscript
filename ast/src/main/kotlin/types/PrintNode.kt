package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class PrintNode(val content: Expression) : ASTNode {
    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitPrint(this)
    }

    override fun toString(): String {
        return "println($content)"
    }
}
