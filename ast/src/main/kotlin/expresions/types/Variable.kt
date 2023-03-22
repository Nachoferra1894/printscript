package expresions.types

import expresions.Expression
import expresions.ExpressionVisitor
import expresions.Operator

class Variable(private val value: String) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitVariable(this)
    }

    override fun addMember(operator: Operator, newMember: Expression): Expression {
        return Operation(this, operator, newMember)
    }

    override fun toString(): String {
        return value
    }
}
