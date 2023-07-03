package strategies

import configurationLinter.ConfigClassesLinter
import configurationLinter.ReadInputCase
import configurationLinter.ReadInputNormal
import configurationLinter.ReadInputOperations
import expresions.Expression
import expresions.types.Operation
import expresions.types.ReadInputExp

class ExpressionStrategy {

    fun checkContent(node: Expression, configClasses: ArrayList<ConfigClassesLinter>): Boolean {
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
