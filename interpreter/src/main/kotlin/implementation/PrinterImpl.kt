package implementation

import interfaces.Printer

class PrinterImpl : Printer {
    override fun print(string: String) {
        println(string)
    }
}
