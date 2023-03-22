package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class AssignmentNode(val name: String, val value: Expression) : ASTNode {

    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitAssignment(this)
    }
}
