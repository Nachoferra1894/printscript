package implementation

import interfaces.ReadInput

class ReadInputImpl : ReadInput {
    override fun read(message: String): String {
        print(message)
        return readLine() ?: ""
    }

}
