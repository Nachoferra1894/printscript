package controllers

import PrototypeType
import Token
import interfaces.ASTNode
import interfaces.SubParser
import kotlinx.coroutines.flow.Flow
import java.util.Queue

interface SubParserController {
    fun getSubParserToken(tokens: Queue<Token>): ASTNode
    fun getExpressionParser(tokens: Queue<Token>): SubParser<*>
    fun declarationTypes(): List<PrototypeType>
}
