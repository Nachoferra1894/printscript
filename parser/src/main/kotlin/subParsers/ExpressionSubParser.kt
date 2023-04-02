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

class ExpressionSubParser(tokens: List<Token>, val closeType: PrototypeType = PrototypeType.SEMICOLON) : SubParser<Expression>, TokenMatcher(tokens) {
    val expressionInitialTypes: List<PrototypeType> =
        listOf(
            *variableTypes.toTypedArray(),
            PrototypeType.IDENTIFIER
        )
    val expressionMiddleTypes: List<PrototypeType> =
        listOf(
            *operatorTypes.toTypedArray(),
            closeType
        )

    override fun getAstNode(nextIndex: Int): Pair<Expression, Int> {
        var index = nextIndex
        var result: Expression = Variable(getNextTokenOrThrowError(index, expressionInitialTypes).value!!)
        index++
        var nextToken: Token = getNextTokenOrThrowError(index, expressionMiddleTypes)
        index++
        while (nextToken.prototypeType != closeType) {
            val currentToken = getNextTokenOrThrowError(index, expressionInitialTypes)
            index++
            val operator: Operator = Operator.getByPrototypeType(nextToken.prototypeType)
            result = result.addMember(operator, Variable(currentToken.value!!))
            nextToken = getNextTokenOrThrowError(index, expressionMiddleTypes)
            index++
        }
        return Pair(result, index)
    }
}
