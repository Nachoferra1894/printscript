package interpreterUtils

class PrinterImpl : Printer {

    private val messages = ArrayList<String>()

    override fun print(value: String) {
        messages.add(value)
        println(value)
    }

    fun getMessages(): List<String?>? {
        return messages
    }

}
