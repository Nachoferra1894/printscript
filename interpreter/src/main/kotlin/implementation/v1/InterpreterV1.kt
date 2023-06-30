package implementation.v1

import implementation.Interpreter
import interfaces.ASTNode
import version.V1

class InterpreterV1 (private val visitor: InterpreterVisitorV1): Interpreter(V1()) {

    override fun interpret(ast: ASTNode) {
        ast.accept(this.visitor)
    }

    override fun getValue(variable: String) : Any? {
        var map = this.getMemory()
        var value = map.getValue(variable)

        return if (value.type === "string"){
            value.value
        }else if(value.type === "number"){
            if (!(value.value as String).contains(".")){
                (value.value as String).toInt()
            } else {
                (value.value as String).toFloat()
            }
        }else{
            throw Error("No existe otro tipo en la version 1");
        }

    }

    private fun getMemory(): InterpreterMapV1 {
        return this.visitor.map
    }

}