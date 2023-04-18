package interfaces

import implementation.InterpreterMap

interface Interpreter {
    fun interpret(ast: ASTNode)
    fun getMemory(): InterpreterMap
}
