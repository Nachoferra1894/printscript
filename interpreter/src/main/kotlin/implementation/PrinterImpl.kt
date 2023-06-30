package implementation

import interfaces.Printer

class PrinterImpl : Printer {
    override fun print(value: String) {
        println(value)
    }

}
