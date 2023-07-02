package implementation

import implementation.v1.InterpreterMapV1
import implementation.v1.InterpreterV1
import implementation.v1.InterpreterVisitorV1
import implementation.v2.InterpreterMapV2
import implementation.v2.InterpreterV2
import implementation.v2.InterpreterVisitorV2
import interfaces.Interpreter
import interpreterUtils.Printer
import interpreterUtils.ReadInput
import interpreterUtils.ReadInputImpl
import version.V1
import version.V2
import version.Version

abstract class Interpreter(
    var version: Version
) : Interpreter {
    companion object InterpreterConstructor {
        fun create(version: Version, printer: Printer, readInput: ReadInput = ReadInputImpl()): Interpreter {
            var interpreter = when (version) {
                is V1 -> InterpreterV1(InterpreterVisitorV1(InterpreterMapV1(mutableMapOf()), printer))
                is V2 -> InterpreterV2(InterpreterVisitorV2(InterpreterMapV2(mutableMapOf()), printer, readInput))
                else -> throw IllegalArgumentException("Versión no soportada")
            }
            return interpreter
        }
    }
}
