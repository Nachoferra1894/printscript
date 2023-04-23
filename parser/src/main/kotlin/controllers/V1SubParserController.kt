package controllers

import PrototypeType
import Token
import exceptions.WrongTokenException
import interfaces.SubParser
import subParsers.AssignmentSubParser
import subParsers.DeclarationSubParser
import subParsers.ExpressionSubParser
import subParsers.PrintSubParser
import variableTypes
import version.V1

class V1SubParserController : SubParserController {
    val version = V1()
    override fun getSubParser(tokens: List<Token>): SubParser<*> {
        return when (tokens[0].prototypeType) {
            PrototypeType.METHOD_PRINT -> PrintSubParser(tokens, version)
            PrototypeType.IDENTIFIER -> AssignmentSubParser(tokens, version)
            in declarationTypes() -> DeclarationSubParser(tokens, version)
            else -> throw WrongTokenException(tokens[0]) // Having a readInput here does not make sense
        }
    }

    override fun getExpressionParser(tokens: List<Token>, index: Int): SubParser<*> {
        return when (tokens[index].prototypeType) {
            PrototypeType.SPACE -> {
                getExpressionParser(tokens, index + 1)
            }

            in variableTypes(version) -> {
                ExpressionSubParser(tokens, version)
            }

            else -> throw WrongTokenException(tokens[index])
        } // Having a readInput here does not make sense
    }

    override fun declarationTypes(): List<PrototypeType> {
        return listOf(PrototypeType.LET)
    }
}
