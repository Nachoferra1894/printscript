package implementation.v2

import implementation.Interpreter
import interfaces.ASTNode

class InterpreterV2(private val visitor: InterpreterVisitorV2) : Interpreter() {

    override fun interpret(ast: ASTNode) {
        ast.accept(this.visitor)
    }

    override fun getValue(variable: String): Any? {
        val map: InterpreterMapV2 = this.getMemory()
        val value: ValueAndTypeV2 = map.getValue(variable)

        if (value.type === "string") {
            return value.value
        } else if (value.type === "number") {
            return if (!(value.value as String).contains(".")) {
                value.value.toInt()
            } else {
                value.value.toFloat()
            }
        } else if (value.type === "boolean") {
            return value.value === "true"
        }

        return value.value
    }

    private fun getMemory(): InterpreterMapV2 {
        return (this.visitor).map
    }
}
