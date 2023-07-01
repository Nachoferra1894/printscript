package types

import interfaces.ASTNode
import interfaces.ASTNodeVisitorCommon

class ParentNode : ASTNode {
    private val children: MutableList<ASTNode> = ArrayList()

    constructor()

    constructor(child: ASTNode) {
        children.add(child)
    }

    constructor(child: List<ASTNode>) {
        children.addAll(child)
    }

    fun addChild(child: ASTNode) {
        children.add(child)
    }

    fun getChildren(): List<ASTNode> {
        return children
    }

    fun getFirstChild(): ASTNode {
        return children[0]
    }

    override fun getLine(): Int {
        return 0
    }

    override fun accept(visitor: ASTNodeVisitorCommon) {
        visitor.visitParentNode(this)
    }

    override fun toString(): String {
        return children.map { it.toString() }.toString()
    }
}
