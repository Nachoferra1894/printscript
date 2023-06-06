package interfaces

import implementation.InterpreterMapV1

interface Interpreter {
    fun interpret(ast: ASTNode)
    fun getMemory(): Any
}

interface InterpreterVisitor {

}
