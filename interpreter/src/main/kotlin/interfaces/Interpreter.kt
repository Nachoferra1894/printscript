package interfaces

interface Interpreter {
    fun interpret(ast: ASTNode)
    fun getValue(variable: String): Any?
    fun getVariableValues(): HashMap<String, Any?>
}
