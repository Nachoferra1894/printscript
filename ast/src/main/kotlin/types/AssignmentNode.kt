package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class AssignmentNode(val name: String, val value: Expression, private val line: Int = 0) : ASTNode {
    override fun getLine(): Int {
        return line
    }

    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitAssignment(this)
    }

    override fun toString(): String {
        return "$name = $value"
    }
}
