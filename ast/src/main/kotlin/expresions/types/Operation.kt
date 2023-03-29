package expresions.types

import expresions.Expression
import expresions.ExpressionVisitor
import expresions.Operator
import interfaces.ASTNodeVisitor

class Operation : Expression {
    private var l: Expression?
    private var operator: Operator? = null
    private var r: Expression? = null

    constructor(value: String) {
        this.l = Variable(value)
    }

    constructor(l: Expression?, operator: Operator?, r: Expression?) {
        this.l = l
        this.operator = operator
        this.r = r
    }

    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitOperation(this)
    }

    override fun accept(visitor: ASTNodeVisitor) {

    }

    override fun addMember(operator: Operator, newMember: Expression): Expression {
        return Operation(this, operator, newMember)
    }

    override fun toString(): String {
        return if (r !== null) {
            "$l $operator $r"
        } else {
            l.toString()
        }
    }
}
