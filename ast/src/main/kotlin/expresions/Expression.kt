package expresions

import interfaces.ASTNode
import interfaces.ASTNodeVisitorCommon

interface Expression : ASTNode {
    override fun accept(visitor: ASTNodeVisitorCommon): Any
    fun addMember(operator: Operator, newMember: Expression): Expression
    override fun toString(): String
}
