package expresions.types

import PrototypeType
import expresions.Expression
import expresions.ExpressionVisitor
import expresions.Operator
import interfaces.ASTNodeVisitor

class Variable(private val value: String, private val variableType: PrototypeType) : Expression {
    init {
        require(variableType == PrototypeType.IDENTIFIER || variableType == PrototypeType.NUMBER || variableType == PrototypeType.STRING) {
            "Variable type must be either IDENTIFIER, NUMBER or STRING"
        }
    }
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitVariable(this)
    }

    override fun accept(visitor: ASTNodeVisitor) {
    }

    override fun addMember(operator: Operator, newMember: Expression): Expression {
        return Operation(this, operator, newMember)
    }

    override fun toString(): String {
        if(variableType == PrototypeType.STRING) return "\"$value\""
        return value
    }
}
