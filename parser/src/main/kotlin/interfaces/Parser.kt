package interfaces

import exceptions.WrongTokenException
import kotlin.jvm.Throws

interface Parser<T : ASTNode> {
    @Throws(WrongTokenException::class)
    fun getASTTree(): T
}
