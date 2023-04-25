package expresions.types

import PrototypeType
import expresions.Expression
import expresions.ExpressionVisitor
import expresions.Operator
import interfaces.ASTNodeVisitorCommon
import version.V1
import version.Version

class Operation : Expression {
    private var l: Expression?
    private var operator: Operator? = null
    private var r: Expression? = null
    private var line: Int
    private var version: Version

    constructor(value: String, variableType: PrototypeType, line: Int = 0, version: Version = V1()) {
        this.line = line
        this.version = version
        this.l = Variable(value, variableType, this.line, version)
    }

    constructor(l: Expression?, operator: Operator?, r: Expression?, line: Int = 0, version: Version = V1()) {
        this.l = l
        this.operator = operator
        this.r = r
        this.line = line
        this.version = version
    }

    fun accept(visitor: ExpressionVisitor) {
        visitor.visitOperation(this)
    }

    override fun accept(visitor: ASTNodeVisitorCommon) {
        visitor.visitExpressionNode(this)
    }

    override fun addMember(operator: Operator, newMember: Expression): Expression {
        return Operation(this, operator, newMember, this.line, this.version)
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
