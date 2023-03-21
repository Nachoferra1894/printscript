package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class AssignmentExpression(val name: String, val value: Expression) : ASTNode {

    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitAssignment(this)
    }
}
