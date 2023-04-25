package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class IfNode(
    condition: Expression,
    private var line: Int = 0,
    private var truthyNode: ASTNode? = null,
    private var falsyNode: ASTNode? = null
) : ASTNode {
    private var condition: Expression? = condition

    override fun getLine(): Int {
        return line
    }

    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitIfNode(this)
    }

    override fun toString(): String {
        return if (falsyNode !== null) {
            "if($condition) {\n$truthyNode\n} else {\n$falsyNode\n}"
        } else {
            "if($condition) {\n$truthyNode\n}"
        }
    }

    fun addTruthyNode(node: ASTNode) {
        truthyNode = node
    }

    fun addFalsyNode(node: ASTNode) {
        falsyNode = node
    }

    fun getTruthyNode(): ASTNode? {
        return truthyNode
    }

    fun getFalsyNode(): ASTNode? {
        return falsyNode
    }
}
