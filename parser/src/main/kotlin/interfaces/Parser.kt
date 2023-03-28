package interfaces

import Token

interface Parser<T: ASTNode> {
    fun getASTTree(): T
}
