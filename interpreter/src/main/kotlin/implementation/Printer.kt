package implementation

import interfaces.Printer

class Printer : Printer{
    override fun print(value: Any) {
        println(value)
    }
}
