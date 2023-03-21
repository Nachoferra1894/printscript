package expresions

import expresions.types.Operation
import expresions.types.Variable

interface ExpressionVisitor {
    fun visitVariable(variable: Variable)
    fun visitOperation(operation: Operation)
}
