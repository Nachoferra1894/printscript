import java.io.InputStream

class MockInputStream(line: String, private val numberOfLines: Int) : InputStream() {
    var lineNumber = 0
    private var index = 0
    private val lineBytes: IntArray

    init {
        lineBytes = line.chars().toArray()
    }

    override fun read(): Int {
        if (index >= lineBytes.size) {
            index = 0
            lineNumber = lineNumber + 1
        }
        return if (lineNumber < numberOfLines) {
            val byteValue = lineBytes[index]
            index = index + 1
            byteValue
        } else {
            -1
        }
    }
}
