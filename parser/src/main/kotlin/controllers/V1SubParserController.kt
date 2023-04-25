package controllers

import PrototypeType
import Token
import exceptions.WrongTokenException
import expresions.Expression
import interfaces.ASTNode
import interfaces.SubParser
import kotlinx.coroutines.runBlocking
import subParsers.AssignmentSubParser
import subParsers.DeclarationSubParser
import subParsers.ExpressionSubParser
import subParsers.PrintSubParser
import variableTypesV2
import version.V1
import java.util.Queue

class V1SubParserController : SubParserController {
    val version = V1()
    override fun getSubParserToken(tokens: Queue<Token>): ASTNode = runBlocking {
        val fToken = tokens.peek()
        when (fToken.prototypeType) {
            PrototypeType.METHOD_PRINT -> PrintSubParser(tokens, version).getAstNode()
            PrototypeType.IDENTIFIER -> AssignmentSubParser(tokens, version).getAstNode()
            in declarationTypes() -> DeclarationSubParser(tokens, version).getAstNode()
            else -> throw WrongTokenException(fToken)
        }
    }

    override fun getExpressionParser(tokens: Queue<Token>): SubParser<Expression> = runBlocking {
        val fToken = tokens.peek()
        when (fToken.prototypeType) {
            PrototypeType.SPACE -> {
                tokens.poll()
                getExpressionParser(tokens)
            }
            in variableTypesV2 -> {
                ExpressionSubParser(tokens, version)
            }
            else -> throw WrongTokenException(fToken)
        } // Having a readInput here does not make sense
    }

    override fun declarationTypes(): List<PrototypeType> {
        return listOf(PrototypeType.LET)
    }
}
