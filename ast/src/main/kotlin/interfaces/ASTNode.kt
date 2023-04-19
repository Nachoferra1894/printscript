package interfaces

interface ASTNode {
    fun getLine(): Int
    fun accept(visitor: ASTNodeVisitor): Any
    override fun toString(): String
}
