package interfaces

interface ASTNode {
    fun accept(visitor: ASTNodeVisitor) : Any
    override fun toString(): String
}
