package implementation

import expresions.types.Variable
import interfaces.Printer

class PrinterImpl : Printer {
    override fun print(value: Any) {
        if (value is Variable) {
            println(value.getValue())
        }
    }
}
