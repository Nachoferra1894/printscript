package subParsers

import PrototypeType
import Token
import TokenMatcher
import expresions.Expression
import expresions.Operator
import expresions.types.Variable
import interfaces.SubParser
import operatorTypes
import variableTypes

class ExpressionSubParser(tokens: List<Token>,val closeType: PrototypeType = PrototypeType.SEMICOLON) : SubParser<Expression>, TokenMatcher(tokens) {
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
        index++
        var nextToken: Token = getNextTokenOrThrowError(index, expressionMiddleTypes)
        index++
        while (!nextToken.prototypeType.equals(closeType)) {
            val currentToken = getNextTokenOrThrowError(index, expressionInitialTypes)
            val operator: Operator = Operator.getByPrototypeType(nextToken.prototypeType)
            result.addMember(operator, Variable(currentToken.value!!))
            nextToken = getNextTokenOrThrowError(index, expressionMiddleTypes)
            index += 2
        }
        return Pair(result, index)
    }
}
