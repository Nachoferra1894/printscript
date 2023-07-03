package strategies

import configurationLinter.ConfigClassesLinter
import configurationLinter.ReadInputCase
import configurationLinter.ReadInputNormal
import configurationLinter.ReadInputOperations
import expresions.Expression
import expresions.types.Operation
import expresions.types.ReadInputExp
import expresions.types.Variable

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
        return exp !is Operation
    }
    private fun isOperation(exp: Expression): Boolean {
        return exp is Operation || exp is Variable
    }

    fun getIncorrectLine(node: Expression): String {
        return "Incorrect readInput format [line: " + node.getLine() + " ]"
    }
}
