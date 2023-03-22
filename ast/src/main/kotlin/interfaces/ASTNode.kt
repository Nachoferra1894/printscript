package interfaces

interface ASTNode {
    fun accept(visitor: ASTNodeVisitor)
    override fun toString(): String
}
