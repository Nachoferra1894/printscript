package implementation.v1

import implementation.Interpreter
import interfaces.ASTNode
import version.V1

class InterpreterV1(private val visitor: InterpreterVisitorV1) : Interpreter(V1()) {

    override fun interpret(ast: ASTNode) {
        ast.accept(this.visitor)
    }

    override fun getValue(variable: String): Any? {
        val map: InterpreterMapV1 = this.getMemory()
        val value: ValueAndTypeV1 = map.getValue(variable)

        if (value.value === null) {
            return null
        }
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

    override fun getVariableValues(): HashMap<String, Any?> {
        val variableValue = HashMap<String, Any?>()
        val memory = getMemory().getMap()
        for (variable in memory) {
            val value = getValue(variable.key)
            if (value !== null) variableValue.put(variable.key, value)
        }
        return variableValue
    }

    private fun getMemory(): InterpreterMapV1 {
        return this.visitor.map
    }
}
