package controllers

import PrototypeType
import Token
import interfaces.SubParser

interface SubParserController {
    fun getSubParser(tokens: List<Token>): SubParser<*>
    fun getExpressionParser(tokens: List<Token>, index: Int): SubParser<*>
    fun declarationTypes(): List<PrototypeType>
}
