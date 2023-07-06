package interpreterUtils

class ReadInputImpl : ReadInput {
    override fun read(message: String): String {
        return readlnOrNull() ?: ""
    }
}
