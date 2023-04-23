package types

import Token
import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class IfNode(
    private val condition: Token? = null,
    private val truthyNode: ASTNode? = null, // TODO delete this and make it non optional
    private val line: Int,
    private val falsyNode: ASTNode? = null
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
