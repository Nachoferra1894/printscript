package interfaces

interface ASTNode {
    fun accept(visitor: ASTNodeVisitor)
}