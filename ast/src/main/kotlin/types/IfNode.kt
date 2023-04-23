package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class IfNode(
    private val condition: Expression,
    private val truthyNode: ParentNode,
    private val line: Int,
    private val falsyNode: ParentNode? = null
) : ASTNode {
    override fun getLine(): Int {
        return line
    }

    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitIfNode(this)
    }

    override fun toString(): String {
        return ("if($condition) {\n$truthyNode\n} else {\n$falsyNode\n}")
    }
}
