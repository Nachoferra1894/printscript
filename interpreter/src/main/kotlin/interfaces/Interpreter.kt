package interfaces

import version.Version

interface Interpreter {
    fun interpret(ast: ASTNode)
    fun getValue(variable: String) : Any?

}

interface InterpreterVisitor {

}
