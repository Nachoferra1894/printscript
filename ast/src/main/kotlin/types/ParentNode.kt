package types

import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class ParentNode: ASTNode {
    private val children: MutableList<ASTNode> = ArrayList()

    fun addChild(child: ASTNode) {
        children.add(child)
    }

    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitParentNode(this)
    }
}
