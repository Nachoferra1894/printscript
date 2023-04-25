package subParsers

import PrototypeType
import Token
import TokenMatcher
import controllers.ControllerGetter
import dataTypes
import exceptions.WrongTokenException
import expresions.Expression
import interfaces.SubParser
import kotlinx.coroutines.flow.Flow
import types.VariableDeclarationNode
import version.Version
import java.util.*

class DeclarationSubParser(tokens: Queue<Token>, private val version: Version) :
    SubParser<VariableDeclarationNode>, TokenMatcher(tokens) {
    // Statement: let g: number = 1 - 2 - 3;
    // Statement: let g: number;

    private val controllerGetter = ControllerGetter()
    private val subParserController = controllerGetter.getController(version)

    override fun getAstNode(): VariableDeclarationNode {
        val declarationType = getNextTokenOrThrowError(subParserController.declarationTypes())
        val variableName = getNextTokenOrThrowError(PrototypeType.IDENTIFIER)
        getNextTokenOrThrowError(PrototypeType.COLON)
        val variableType = getNextTokenOrThrowError(dataTypes(version))
        val isMutable: Boolean = declarationType.prototypeType == PrototypeType.LET
        try {
            getEOL()
            return VariableDeclarationNode(
                variableName.value!!,
                variableType.prototypeType.toString(),
                variableName.line,
                isMutable
            )
        } catch (e: WrongTokenException) {
            getNextTokenOrThrowError(PrototypeType.EQUALS)
            val subParser = subParserController.getExpressionParser(tokens)
            val expression = subParser.getAstNode()
            return VariableDeclarationNode(
                variableName.value!!,
                variableType.prototypeType.toString(),
                expression as Expression,
                variableName.line,
                isMutable
            )
        }
    }
}
