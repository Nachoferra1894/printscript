package subParsers

import PrototypeType
import Token
import TokenMatcher
import controllers.ControllerGetter
import dataTypes
import exceptions.WrongTokenException
import expresions.Expression
import interfaces.SubParser
import types.VariableDeclarationNode
import version.Version

class DeclarationSubParser(private val tokens: List<Token>, private val version: Version) :
    SubParser<VariableDeclarationNode>, TokenMatcher(tokens) {
    // Statement: let g: number = 1 - 2 - 3;
    // Statement: let g: number;

    private val controllerGetter = ControllerGetter()
    private val subParserController = controllerGetter.getController(version)

    override fun getAstNode(nextIndex: Int): Pair<VariableDeclarationNode, Int> {
        var (declarationType, index) = getNextTokenOrThrowError(nextIndex, subParserController.declarationTypes())
        val identifier = getNextTokenOrThrowError(index, PrototypeType.IDENTIFIER)
        val variableName = identifier.first
        index = identifier.second
        index = getNextTokenOrThrowError(index, PrototypeType.COLON).second
        val variable = getNextTokenOrThrowError(index, dataTypes(version))
        val variableType = variable.first
        index = variable.second
        val isMutable: Boolean = declarationType.prototypeType == PrototypeType.LET
        try {
            index = getEOL(index).second
            val newNode = VariableDeclarationNode(
                variableName.value!!,
                variableType.prototypeType.toString(),
                variableName.line,
                isMutable
            )
            return Pair(newNode, index)
        } catch (e: WrongTokenException) {
            index = getNextTokenOrThrowError(index, PrototypeType.EQUALS).second
            val subParser = subParserController.getExpressionParser(tokens, index)
            val (expression, expressionIndex) = subParser.getAstNode(index)
            val newNode = VariableDeclarationNode(
                variableName.value!!,
                variableType.prototypeType.toString(),
                expression as Expression,
                variableName.line,
                isMutable
            )
            return Pair(newNode, expressionIndex)
        }
    }
}
