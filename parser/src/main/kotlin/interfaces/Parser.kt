package interfaces

interface Parser<T : ASTNode> {
    fun getASTTree(): T
}
