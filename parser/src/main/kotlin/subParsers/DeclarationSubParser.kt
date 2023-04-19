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
    private val expressionSubParser = ExpressionSubParser(tokens)

    override fun getAstNode(nextIndex: Int): Pair<VariableDeclarationNode, Int> {
        var (declarationType, index) = getNextTokenOrThrowError(nextIndex, declarationTypes)
        val identifier = getNextTokenOrThrowError(index, PrototypeType.IDENTIFIER)
        val variableName = identifier.first
        index = identifier.second
        index = getNextTokenOrThrowError(index, PrototypeType.COLON).second
        val variable = getNextTokenOrThrowError(index, dataTypes)
        val variableType = variable.first
        index = variable.second
        val isMutable: Boolean = declarationType.prototypeType == PrototypeType.LET
        return try {
            index = getNextTokenOrThrowError(index, PrototypeType.SEMICOLON).second
            val newNode = VariableDeclarationNode(
                variableName.value!!,
                variableType.prototypeType.toString(),
                variableName.line,
                isMutable
            )
            Pair(newNode, index + 1)
        } catch (e: WrongTokenException) {
            index = getNextTokenOrThrowError(index, PrototypeType.ASSIGNATION).second
            val (expression, expressionIndex) = expressionSubParser.getAstNode(index)
            val newNode = VariableDeclarationNode(
                variableName.value!!,
                variableType.prototypeType.toString(),
                expression,
                variableName.line,
                isMutable
            )
            Pair(newNode, expressionIndex)
        }
    }
}
