package strategies

import configuration.ConfigClasses
import configuration.ReadInputCase
import configuration.ReadInputNormal
import configuration.ReadInputOperations
import expresions.Expression
import expresions.types.Operation
import expresions.types.ReadInputExp

class ExpressionStrategy {

    fun checkContent(node: Expression, configClasses: ArrayList<ConfigClasses>): Boolean {
        if (node is ReadInputExp) {
            return when (configClasses.find { it is ReadInputCase } as ReadInputCase) {
                is ReadInputNormal -> isNormal(node.expression)
                is ReadInputOperations -> isOperation(node.expression)
            }
        }
        return true
    }

    private fun isNormal(exp: Expression): Boolean {
        if (exp is Operation) {
            return false
        }
        return true
    }
    private fun isOperation(exp: Expression): Boolean {
        if (exp is Operation) {
            return true
        }
        return false
    }

    fun getIncorrectLine(node: Expression): String {
        return "Incorrect readInput format [line: " + node.getLine() + " ]"
    }
}
