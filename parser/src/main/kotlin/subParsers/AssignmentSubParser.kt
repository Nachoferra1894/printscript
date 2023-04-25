package subParsers

import Token
import TokenMatcher
import controllers.ControllerGetter
import expresions.Expression
import interfaces.SubParser
import kotlinx.coroutines.flow.Flow
import types.AssignmentNode
import version.Version
import java.util.Queue

class AssignmentSubParser(tokens: Queue<Token>, version: Version) : SubParser<AssignmentNode>, TokenMatcher(tokens) {
    private val controllerGetter = ControllerGetter()
    private val subParserController = controllerGetter.getController(version)

    override fun getAstNode(): AssignmentNode {
        val variableName = getNextTokenOrThrowError(PrototypeType.IDENTIFIER)
        getNextTokenOrThrowError(PrototypeType.EQUALS)
        val expressionSubParser = subParserController.getExpressionParser(tokens)
        val expression = expressionSubParser.getAstNode()
        return AssignmentNode(variableName.value!!, expression as Expression, expression.getLine())
    }
}
