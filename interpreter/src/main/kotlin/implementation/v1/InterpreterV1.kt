package implementation.v1

import implementation.Interpreter
import interfaces.ASTNode

class InterpreterV1(private val visitor: InterpreterVisitorV1) : Interpreter() {

    override fun interpret(ast: ASTNode) {
        ast.accept(this.visitor)
    }

    override fun getValue(variable: String): Any? {
        val map: InterpreterMapV1 = this.getMemory()
        val value: ValueAndTypeV1 = map.getValue(variable)

        if (value.type === "string") {
            return value.value
        } else if (value.type === "number") {
            return if (!(value.value as String).contains(".")) {
                value.value.toInt()
            } else {
                value.value.toFloat()
            }
        }

        return value.value
    }

    private fun getMemory(): InterpreterMapV1 {
        return this.visitor.map
    }
}
