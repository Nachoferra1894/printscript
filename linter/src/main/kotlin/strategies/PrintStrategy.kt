package strategies

import expresions.types.Operation
import types.PrintNode

class PrintStrategy {
    var condition = true

    fun checkContent(node: PrintNode) : Boolean {
        val content = node.content
        if(condition && content is Operation) return false
        return true
   }

    fun getIncorrectLine(node: PrintNode) : String {
        return "Incorrect println format [line: " + node.getLine() + " ]"
    }

}