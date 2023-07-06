package implementation.v2

import implementation.Interpreter
import interfaces.ASTNode
import version.V2

class InterpreterV2(private val visitor: InterpreterVisitorV2) : Interpreter(V2()) {

    override fun interpret(ast: ASTNode) {
        ast.accept(this.visitor)
    }

    override fun getValue(variable: String): Any? {
        val map: InterpreterMapV2 = this.getMemory()
        val value: ValueAndTypeV2 = map.getValue(variable)

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
        } else if (value.type === "boolean") {
            return value.value === "true"
        }

        return value.value
    }
    override fun getVariableValues(): HashMap<String, Any?> {
        val variableValue = HashMap<String, Any?>()
        val memory = getMemory().getMap()
        for (variable in memory) {
            variableValue.put(variable.key, getValue(variable.key))
        }
        return variableValue
    }

    private fun getMemory(): InterpreterMapV2 {
        return (this.visitor).map
    }
}
