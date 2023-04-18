package implementation

import interfaces.ASTNode
import interfaces.Interpreter

class Interpreter(
    private val visitor: InterpreterVisitor
) : Interpreter {

    override fun interpret(ast: ASTNode) {
        ast.accept(this.visitor)
    }

    override fun getMemory(): InterpreterMap {
        return visitor.map
    }

    companion object InterpreterConstructor {
        fun create(): Interpreter = Interpreter(InterpreterVisitor(InterpreterMap(mutableMapOf()), PrinterImpl()))
    }
}
