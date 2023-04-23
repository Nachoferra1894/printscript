package implementation

import interfaces.ASTNode
import interfaces.Interpreter
import version.Version

class Interpreter(
    private val visitor: InterpreterVisitor,
    private var version: Version
) : Interpreter {

    override fun interpret(ast: ASTNode) {
        ast.accept(this.visitor)
    }

    override fun getMemory(): InterpreterMap {
        return visitor.map
    }

    companion object InterpreterConstructor {
        fun create(version: Version): Interpreter = Interpreter(InterpreterVisitor(InterpreterMap(mutableMapOf()), PrinterImpl(), version), version)
    }
}
