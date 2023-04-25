package controllers

import PrototypeType
import Token
import exceptions.WrongTokenException
import interfaces.ASTNode
import interfaces.SubParser
import kotlinx.coroutines.runBlocking
import subParsers.AssignmentSubParser
import subParsers.DeclarationSubParser
import subParsers.ExpressionSubParser
import subParsers.IfSubParser
import subParsers.PrintSubParser
import subParsers.ReadInputSubParser
import variableTypesV2
import version.V2
import java.util.*

class V2SubParserController : SubParserController {
    val version = V2()
    override fun getSubParserToken(tokens: Queue<Token>): ASTNode = runBlocking {
        val fToken = tokens.peek()
        when (fToken.prototypeType) {
            PrototypeType.METHOD_PRINT -> PrintSubParser(tokens, version).getAstNode()
            PrototypeType.IDENTIFIER -> AssignmentSubParser(tokens, version).getAstNode()
            in declarationTypes() -> DeclarationSubParser(tokens, version).getAstNode()
            PrototypeType.IF -> IfSubParser(tokens, version).getAstNode()
            else -> throw WrongTokenException(fToken) // Having a readInput here does not make sense
        }
    }

    override fun getExpressionParser(tokens: Queue<Token>): SubParser<*> = runBlocking {
        val fToken = tokens.peek()
        when (fToken.prototypeType) {
            PrototypeType.SPACE -> {
                tokens.poll()
                getExpressionParser(tokens)
            }
            in variableTypesV2 -> {
                ExpressionSubParser(tokens, version)
            }
            PrototypeType.METHOD_READ_INPUT -> {
                ReadInputSubParser(tokens, version)
            }
            else -> {
                throw WrongTokenException(fToken) // Having a readInput here does not make sense
            }
        }
    }

    override fun declarationTypes(): List<PrototypeType> {
        return listOf(PrototypeType.LET, PrototypeType.CONST)
    }
}
