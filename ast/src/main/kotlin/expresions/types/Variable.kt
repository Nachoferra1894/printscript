package expresions.types

import PrototypeType
import expresions.Expression
import expresions.ExpressionVisitor
import expresions.Operator
import interfaces.ASTNodeVisitorCommon
import variableTypes
import version.V1
import version.Version

class Variable(
    private val value: String,
    private val variableType: PrototypeType,
    private val line: Int = 0,
    private val version: Version = V1()
) : Expression {
    init {
        require(variableTypes(version).contains(variableType)) {
            "Variable type must be either ${variableTypes(version).joinToString(", ")} but was $variableType"
        }
    }

    fun accept(visitor: ExpressionVisitor) {
        visitor.visitVariable(this)
    }

    override fun accept(visitor: ASTNodeVisitorCommon) {
        visitor.visitExpressionNode(this)
    }

    override fun addMember(operator: Operator, newMember: Expression): Expression {
        return Operation(this, operator, newMember, this.line, this.version)
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
