package expresions.types

import expresions.Expression
import expresions.ExpressionVisitor
import expresions.Operator
import interfaces.ASTNodeVisitorCommon

// ReadInput is an expression because it behaves like so, it returns a value
class ReadInputExp(var expression: Expression, private val line: Int) : Expression {

    override fun addMember(operator: Operator, newMember: Expression): Expression {
        return Operation(this, operator, newMember, this.line) // ReadInputExp is only used in V2
    }

    fun accept(visitor: ExpressionVisitor) {
        visitor.visitReadInput(this)
    }

    override fun accept(visitor: ASTNodeVisitorCommon) {
        visitor.visitExpressionNode(this)
    }

    override fun toString(): String {
        return "ReadInput($expression)"
    }

    override fun getLine(): Int {
        return line
    }
}
