package interfaces

import excepntions.InvalidTokenException
import exceptions.WrongTokenException
import kotlin.jvm.Throws

interface SubParser<T : ASTNode> {
    @Throws(WrongTokenException::class, InvalidTokenException::class)
    fun getAstNode(nextIndex: Int): Pair<T, Int>
}
