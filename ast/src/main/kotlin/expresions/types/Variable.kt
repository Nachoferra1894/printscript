package expresions.types

import PrototypeType
import expresions.Expression
import expresions.Operator
import interfaces.ASTNodeVisitorCommon

class Variable(
    private val value: String,
    private val variableType: PrototypeType,
    private val line: Int = 0
) : Expression {

    override fun accept(visitor: ASTNodeVisitorCommon) {
        visitor.visitExpressionNode(this)
    }

    override fun addMember(operator: Operator, newMember: Expression): Expression {
        return Operation(this, operator, newMember, this.line)
    }

    override fun toString(): String {
        if (variableType == PrototypeType.STRING) return "\"$value\""
        return value
    }

    fun getValue(): String {
        return this.value
    }

    fun getType(): PrototypeType {
        return this.variableType
    }

    override fun getLine(): Int {
        return line
    }
}
