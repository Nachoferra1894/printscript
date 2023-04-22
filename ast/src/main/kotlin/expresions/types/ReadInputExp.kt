package expresions.types

import expresions.Expression
import expresions.ExpressionVisitor
import expresions.Operator
import interfaces.ASTNodeVisitor
import version.V2

// ReadInput is an expression because it behaves like so, it returns a value
class ReadInputExp(var expression: Expression, private val line: Int) : Expression {

    override fun addMember(operator: Operator, newMember: Expression): Expression {
        return Operation(this, operator, newMember, this.line, V2()) // ReadInputExp is only used in V2
    }

    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitReadInput(this)
    }

    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitExpressionNode(this)
    }

    override fun toString(): String {
        return "ReadInput($expression)"
    }

    override fun getLine(): Int {
        return line
    }
}
