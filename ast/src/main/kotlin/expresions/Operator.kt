package expresions

import PrototypeType
import excepntions.InvalidTokenException

enum class Operator(op: String) {
    SUM("+"), SUB("-"), DIV("/"), MUL("*");

    companion object {
        fun getByPrototypeType(prototypeType: PrototypeType): Operator {
            return when (prototypeType) {
                PrototypeType.PLUS -> SUM
                PrototypeType.SUBTRACTION -> SUB
                PrototypeType.DIVISION -> DIV
                PrototypeType.MULTIPLICATION -> MUL
                else -> throw InvalidTokenException(prototypeType)
            }
        }
    }
}
