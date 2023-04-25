package interfaces

interface ASTNode {
    fun getLine(): Int
    fun accept(visitor: ASTNodeVisitorCommon): Any
    override fun toString(): String
}
