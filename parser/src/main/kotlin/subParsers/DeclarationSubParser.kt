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
        index = getNextTokenOrThrowError(index, declarationTypes).second
        val identifier = getNextTokenOrThrowError(index, PrototypeType.IDENTIFIER)
        val variableName = identifier.first
        index = identifier.second
        index = getNextTokenOrThrowError(index, PrototypeType.COLON).second
        val variable = getNextTokenOrThrowError(index, dataTypes)
        val variableType = variable.first
        index = variable.second
        return try {
            index = getNextTokenOrThrowError(index, PrototypeType.SEMICOLON).second
            val newNode = VariableDeclarationNode(variableName.value!!, variableType.prototypeType.toString(), variableName.line)
            Pair(newNode, index)
        } catch (e: WrongTokenException) {
            index = getNextTokenOrThrowError(index, PrototypeType.ASSIGNATION).second
            val (expression, expressionIndex) = expressionSubParser.getAstNode(index)
            val newNode = VariableDeclarationNode(variableName.value!!, variableType.prototypeType.toString(), expression, variableName.line)
            Pair(newNode, expressionIndex)
        }
    }
}
