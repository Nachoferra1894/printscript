package expresions

interface Expression {
    fun accept(visitor: ExpressionVisitor)
    fun addMember(operator: Operator, newMember: Expression): Expression
}
