package implementation.v2

import implementation.Interpreter
import implementation.PrinterImpl
import implementation.ReadInputImpl
import interfaces.ASTNode
import version.V2


class InterpreterV2 (private val visitor: InterpreterVisitorV2) : Interpreter(V2()) {

    override fun interpret(ast: ASTNode) {
        ast.accept(this.visitor)
    }

    override fun getValue(variable: String) : Any? {
        val map = this.getMemory()
        val value = map.getValue(variable)

        if (value.type === "string"){
            return value.value
        }else if(value.type === "number"){
            if ((value.value as String).contains(".")){
                return (value.value as String).toFloat()
            } else {
                return (value.value as String).toInt()
            }
        }else if(value.type === "boolean"){
            return value.value === "true"
        }

        return value.value
    }

    private fun getMemory(): InterpreterMapV2 {
        return (this.visitor).map
    }

}