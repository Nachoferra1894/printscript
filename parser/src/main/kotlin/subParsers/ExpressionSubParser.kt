package subParsers

import PrototypeType
import Token
import TokenMatcher
import excepntions.InvalidTokenException
import exceptions.WrongTokenException
import expresions.Expression
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import functionTypes
import interfaces.SubParser
import operatorTypes
import parenthesisTypes
import variableTypes

class ExpressionSubParser(tokens: List<Token>) : SubParser<Expression>, TokenMatcher(tokens) {
    val expressionInitialTypes: List<PrototypeType> =
            listOf(
                    *variableTypes.toTypedArray(),
                    PrototypeType.IDENTIFIER
            )
    val expressionMiddleTypes: List<PrototypeType> =
            listOf(
                    *operatorTypes.toTypedArray(),
                    PrototypeType.SEMICOLON
            )

    override fun getAstNode(nextIndex: Int): Pair<Expression, Int> {
        var index = nextIndex
        val result: Expression = Variable(getNextTokenOrThrowError(index, expressionInitialTypes).value!!)
        index++;
        var nextToken: Token = getNextTokenOrThrowError(index, expressionMiddleTypes)
        index++
        while (!nextToken.isEOL()) {
            val currentToken = getNextTokenOrThrowError(index, expressionInitialTypes)
            val operator: Operator = Operator.getByPrototypeType(nextToken.prototypeType)
            result.addMember(operator, Variable(currentToken.value!!))
            nextToken = getNextTokenOrThrowError(index, expressionMiddleTypes)
            index+=2
        }
        return Pair(result, index)
    }
}
