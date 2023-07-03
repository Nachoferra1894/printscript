package strategies

import configurationLinter.*
import expresions.Expression
import expresions.types.Operation
import expresions.types.Variable
import types.PrintNode

class LinterPrintStrategy {

    fun checkContent(node: PrintNode, configClasses: ArrayList<ConfigClassesLinter>): Boolean {
        val case: PrintCase = configClasses.find { it is PrintCase } as PrintCase
        val content = node.content
        return when (case) {
            is PrintNormal -> isPrint(content)
            is PrintOperations -> isOperation(content)
        }
    }

    private fun isPrint(exp: Expression): Boolean {
        return exp !is Operation
    }
    private fun isOperation(exp: Expression): Boolean {
        return exp is Operation || exp is Variable || exp is ReadInputOperations
    }
    fun getIncorrectLine(node: PrintNode): String {
        return "Incorrect println format [line: " + node.getLine() + " ]"
    }
}
