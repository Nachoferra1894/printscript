package expresions

import interfaces.ASTNode

interface Expression : ASTNode {
    fun accept(visitor: ExpressionVisitor)
    fun addMember(operator: Operator, newMember: Expression): Expression
    override fun toString(): String
}
