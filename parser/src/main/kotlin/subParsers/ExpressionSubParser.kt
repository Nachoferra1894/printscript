package subParsers

import PrototypeType
import Token
import TokenMatcher
import exceptions.WrongTokenException
import expresions.Expression
import expresions.Operator
import expresions.types.Variable
import interfaces.SubParser
import operatorTypes
import variableTypesV1
import variableTypesV2
import version.V1
import version.Version
import java.util.*

class ExpressionSubParser(
    tokens: Queue<Token>,
    private val version: Version,
    private val closeType: PrototypeType = PrototypeType.SEMICOLON
) : SubParser<Expression>, TokenMatcher(tokens) {

    private val variableTypes = if (version == V1()) variableTypesV1 else variableTypesV2
    private val expressionMiddleTypes: List<PrototypeType> =
        listOf(
            *operatorTypes.toTypedArray(),
            closeType
        )

    override fun getAstNode(): Expression {
        val variable = getNextTokenOrThrowError(variableTypes)
        var result: Expression = Variable(variable.value!!, variable.prototypeType, variable.line)
        try {
            getEOL()
        } catch (e: WrongTokenException) {
            var expressionMiddleType = getNextTokenOrThrowError(expressionMiddleTypes)
            while (expressionMiddleType.prototypeType != closeType) {
                val opNode = getNextTokenOrThrowError(variableTypes)
                val operator: Operator = Operator.getByPrototypeType(expressionMiddleType.prototypeType)
                result = result.addMember(
                    operator,
                    Variable(opNode.value!!, opNode.prototypeType, opNode.line)
                )
                expressionMiddleType = getNextTokenOrThrowError(expressionMiddleTypes)
            }
        }
        return result
    }
}
