package interfaces

import exceptions.InvalidTokenException
import exceptions.WrongTokenException
import kotlin.jvm.Throws

interface SubParser<T : ASTNode> {
    @Throws(WrongTokenException::class, InvalidTokenException::class)
    fun getAstNode(): T
}
