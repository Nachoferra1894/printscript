package expresions.types

import PrototypeType
import expresions.Expression
import expresions.ExpressionVisitor
import expresions.Operator
import interfaces.ASTNodeVisitor
import variableTypes

class Variable(private val value: String, private val variableType: PrototypeType) : Expression {
    init {
        require(variableTypes.contains(variableType)) {
            "Variable type must be either IDENTIFIER, NUMBER or STRING"
        }
    }
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitVariable(this)
    }

    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitExpressionNode(this)
    }

    override fun addMember(operator: Operator, newMember: Expression): Expression {
        return Operation(this, operator, newMember)
    }

    override fun toString(): String {
        if (variableType == PrototypeType.STRING) return "\"$value\""
        return value
    }

    fun getValue() : String {
        return this.value
    }

    fun getType() : PrototypeType {
        return this.variableType
    }
}
