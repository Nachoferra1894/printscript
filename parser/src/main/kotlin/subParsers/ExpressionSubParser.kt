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
import version.Version

class ExpressionSubParser(tokens: List<Token>, private val version: Version, val closeType: PrototypeType = PrototypeType.SEMICOLON) : SubParser<Expression>, TokenMatcher(tokens) {

    val expressionMiddleTypes: List<PrototypeType> =
        listOf(
            *operatorTypes.toTypedArray(),
            closeType
        )

    override fun getAstNode(nextIndex: Int): Pair<Expression, Int> {
        val expressionInitial = getNextTokenOrThrowError(nextIndex, variableTypes(version))
        var index = expressionInitial.second
        val variable = expressionInitial.first
        var result: Expression = Variable(variable.value!!, variable.prototypeType, variable.line, version)
        val expressionMiddleType = getNextTokenOrThrowError(index, expressionMiddleTypes)
        var nextToken = expressionMiddleType.first
        index = expressionMiddleType.second
        while (nextToken.prototypeType != closeType) {
            val opNode = getNextTokenOrThrowError(index, variableTypes(version))
            val currentToken = opNode.first
            index = opNode.second
            val operator: Operator = Operator.getByPrototypeType(nextToken.prototypeType)
            result = result.addMember(operator, Variable(currentToken.value!!, currentToken.prototypeType, currentToken.line))
            val leftExp = getNextTokenOrThrowError(index, expressionMiddleTypes)
            index = leftExp.second
            nextToken = leftExp.first
        }
        return Pair(result, index)
    }
}
