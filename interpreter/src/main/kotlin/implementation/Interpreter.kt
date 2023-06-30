package implementation

import interfaces.ASTNode
import interfaces.Interpreter
import version.V1
import version.V2
import version.Version

class Interpreter(
    private val visitor: Any,
    private var version: Version
) : Interpreter {

    override fun interpret(ast: ASTNode) {
        if (this.version is V1) {
            ast.accept(visitor as InterpreterVisitorV1)
        } else if (this.version is V2) {
            ast.accept(this.visitor as InterpreterVisitorV2)
        }
        throw Error("Version no soportada!")
    }

    override fun getMemory(): Any {
        if (this.version is V1) {
            return (this.visitor as InterpreterVisitorV1).map
        } else if (this.version is V2) {
            return (this.visitor as InterpreterVisitorV2).map
        }
        throw IllegalStateException("Versión no soportada")
    }

    fun getVersion() : Version {
        return this.version
    }

    companion object InterpreterConstructor {
        fun create(version: Version) : Interpreter {
            var interpreter = when (version) {
                is V1 -> Interpreter(InterpreterVisitorV1(InterpreterMapV1(mutableMapOf()), PrinterImpl()), version)
                is V2 -> Interpreter(InterpreterVisitorV2(InterpreterMapV2(mutableMapOf()), PrinterImpl(), ReadInputImpl()), version)
                else -> throw IllegalArgumentException("Versión no soportada")
            }
            return interpreter
        }
    }
}
