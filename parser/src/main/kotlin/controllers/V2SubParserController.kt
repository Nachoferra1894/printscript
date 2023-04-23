package controllers

import PrototypeType
import Token
import exceptions.WrongTokenException
import interfaces.SubParser
import subParsers.AssignmentSubParser
import subParsers.DeclarationSubParser
import subParsers.ExpressionSubParser
import subParsers.IfSubParser
import subParsers.PrintSubParser
import subParsers.ReadInputSubParser
import variableTypes
import version.V2

class V2SubParserController : SubParserController {
    val version = V2()
    override fun getSubParser(tokens: List<Token>): SubParser<*> {
        return when (tokens[0].prototypeType) {
            PrototypeType.METHOD_PRINT -> PrintSubParser(tokens, version)
            PrototypeType.IDENTIFIER -> AssignmentSubParser(tokens, version)
            in declarationTypes() -> DeclarationSubParser(tokens, version)
            PrototypeType.IF -> IfSubParser(tokens, version)
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
            PrototypeType.METHOD_READ_INPUT -> {
                ReadInputSubParser(tokens, version)
            }
            else -> {
                throw WrongTokenException(tokens[index]) // Having a readInput here does not make sense
            }
        }
    }

    override fun declarationTypes(): List<PrototypeType> {
        return listOf(PrototypeType.LET, PrototypeType.CONST)
    }
}
