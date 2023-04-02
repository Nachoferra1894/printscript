package subParsers

import PrototypeType
import Token
import TokenMatcher
import dataTypes
import declarationTypes
import exceptions.WrongTokenException
import interfaces.SubParser
import types.VariableDeclarationNode

class DeclarationSubParser(tokens: List<Token>) : SubParser<VariableDeclarationNode>, TokenMatcher(tokens) {
    // Statement: let g: number = 1 - 2 - 3;
    // Statement: let g: number;
    val expressionSubParser = ExpressionSubParser(tokens)

    override fun getAstNode(nextIndex: Int): Pair<VariableDeclarationNode, Int> {
        var index = nextIndex
        getNextTokenOrThrowError(index, declarationTypes)
        index++
        val variableName = getNextTokenOrThrowError(index, PrototypeType.IDENTIFIER)
        index++
        getNextTokenOrThrowError(index, PrototypeType.COLON)
        index++
        val variableType = getNextTokenOrThrowError(index, dataTypes)
        index++
        return try {
            getNextTokenOrThrowError(index, PrototypeType.SEMICOLON)
            Pair(VariableDeclarationNode(variableName.value!!, variableType.value!!), index + 1)
        } catch (e: WrongTokenException) {
            getNextTokenOrThrowError(index, PrototypeType.ASSIGNATION)
            index++
            val (expression, expressionIndex) = expressionSubParser.getAstNode(index)
            val newNode = VariableDeclarationNode(variableName.value!!, variableType.prototypeType.toString(), expression)
            Pair(newNode, expressionIndex)
        }
    }
}
