package expresions

import expresions.types.Operation
import expresions.types.ReadInputExp
import expresions.types.Variable

interface ExpressionVisitor {
    fun visitVariable(variable: Variable)
    fun visitOperation(operation: Operation)
    fun visitReadInput(readInputExp: ReadInputExp)
}
