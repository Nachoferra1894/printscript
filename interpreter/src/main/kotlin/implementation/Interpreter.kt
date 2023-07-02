package implementation

import implementation.v1.InterpreterMapV1
import implementation.v1.InterpreterV1
import implementation.v1.InterpreterVisitorV1
import interfaces.Interpreter

abstract class Interpreter : Interpreter {
    companion object InterpreterConstructor {
        fun create(): Interpreter {
            return InterpreterV1(InterpreterVisitorV1(InterpreterMapV1(mutableMapOf()), PrinterImpl()))
        }
    }
}
