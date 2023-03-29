package subParsers

import PrototypeType
import Token
import TokenMatcher
import excepntions.InvalidTokenException
import exceptions.WrongTokenException
import expresions.Expression
import functionTypes
import interfaces.ASTNode
import interfaces.Parser
import interfaces.SubParser
import parenthesisTypes
import types.AssignmentNode
import variableTypes

class ExpressionSubParser(tokens: List<Token>) : SubParser<Expression>, TokenMatcher(tokens) {
    val expressionInitialTypes: List<PrototypeType> =
        listOf(
            PrototypeType.SUBTRACTION,
            PrototypeType.PLUS,
            *functionTypes.toTypedArray(),
            *variableTypes.toTypedArray(),
            *parenthesisTypes.toTypedArray(),
        )

    override fun getAstNode(nextIndex: Int): Pair<Expression, Int> {
        var index = nextIndex
        var currentToken: Token = getNextTokenOrThrowError(index, expressionInitialTypes)
        index++;
        var nextToken: Token = when (currentToken.prototypeType) {
            PrototypeType.SUBTRACTION, PrototypeType.PLUS -> getNextTokenOrThrowError(index, variableTypes)
            in functionTypes -> getNextTokenOrThrowError(index, PrototypeType.OPEN_PARENTHESIS)
            in variableTypes -> getNextTokenOrThrowError(
                index,
                expressionInitialTypes.filter { functionTypes.contains(it) })

            in parenthesisTypes -> getNextTokenOrThrowError(index, expressionInitialTypes)
            else -> throw WrongTokenException(currentToken)
        }
        TODO()

    }

    private fun isEOL(nextToken: Token): Boolean {
        return nextToken.prototypeType === PrototypeType.SEMICOLON
    }

}
