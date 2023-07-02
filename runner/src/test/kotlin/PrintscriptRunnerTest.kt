import input.InputStreamInput
import input.LexerFileInput
import org.junit.jupiter.api.Test
import printscript.CommonPrintScriptRunner
import printscript.PrintscriptRunner
import java.io.File
import java.io.InputStream

class PrintscriptRunnerTest {

    private var numberOfLines = 0
    private lateinit var lineBytes: IntArray

    private val input = MockInputStream("println('hello world');\n", 32 * 1024)
    val inputStreamInput = InputStreamInput(input)

    @Test
    fun testWithCounter() {
        val runner: PrintscriptRunner = CommonPrintScriptRunner()
        val flow = inputStreamInput.getFlow()
        runner.runExecution(flow, { println(it) }, { "" }, CommonErrorHandler());
    }

}
