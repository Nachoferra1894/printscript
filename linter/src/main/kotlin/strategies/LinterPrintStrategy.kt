package strategies

import configurationLinter.ConfigClasses
import configurationLinter.PrintCase
import configurationLinter.PrintNormal
import configurationLinter.PrintOperations
import expresions.Expression
import expresions.types.Operation
import types.PrintNode

class LinterPrintStrategy {

    fun checkContent(node: PrintNode, configClasses: ArrayList<ConfigClasses>): Boolean {
        val case: PrintCase = configClasses.find { it is PrintCase } as PrintCase
        val content = node.content
        return when (case) {
            is PrintNormal -> isPrint(content)
            is PrintOperations -> isOperation(content)
        }
    }

    private fun isPrint(exp: Expression): Boolean {
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
    fun getIncorrectLine(node: PrintNode): String {
        return "Incorrect println format [line: " + node.getLine() + " ]"
    }
}
