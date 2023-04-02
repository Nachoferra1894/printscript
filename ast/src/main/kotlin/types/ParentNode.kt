package types

import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class ParentNode : ASTNode {
    private val children: MutableList<ASTNode> = ArrayList()

    constructor()

    constructor(child: List<ASTNode>) {
        children.addAll(child)
    }

    fun addChild(child: ASTNode) {
        children.add(child)
    }

    fun getChildren(): List<ASTNode> {
        return children
    }

    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitParentNode(this)
    }

    override fun toString(): String {
        return children.map { it.toString() }.toString()
    }
}
