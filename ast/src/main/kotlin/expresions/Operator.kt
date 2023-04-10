package expresions

import PrototypeType
import exceptions.InvalidTokenException

enum class Operator(val op: String) {
    SUM("+"), SUB("-"), DIV("/"), MUL("*");

    override fun toString(): String {
        return op
    }
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
