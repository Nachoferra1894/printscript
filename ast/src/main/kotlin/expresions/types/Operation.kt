package expresions.types

import PrototypeType
import expresions.Expression
import expresions.ExpressionVisitor
import expresions.Operator
import interfaces.ASTNodeVisitor

class Operation : Expression {
    private var l: Expression?
    private var operator: Operator? = null
    private var r: Expression? = null
    private var line: Int
    constructor(value: String, variableType: PrototypeType, line: Int) {
        this.line = line
        this.l = Variable(value, variableType, this.line)
    }

    constructor(l: Expression?, operator: Operator?, r: Expression?, line: Int) {
        this.l = l
        this.operator = operator
        this.r = r
        this.line = line
    }

    override fun accept(visitor: ExpressionVisitor) {
        visitor.visitOperation(this)
    }

    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitExpressionNode(this)
    }

    override fun addMember(operator: Operator, newMember: Expression): Expression {
        return Operation(this, operator, newMember, this.line)
    }

    override fun toString(): String {
        return if (r !== null) {
            "$l $operator $r"
        } else {
            l.toString()
        }
    }

    fun getL(): Expression? {
        return this.l
    }
    fun getOperator(): Operator? {
        return this.operator
    }

    fun getR(): Expression? {
        return this.r
    }

    override fun getLine(): Int {
        return line
    }
}
