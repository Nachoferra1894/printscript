import input.InputStreamInput
import input.LexerFileInput
import org.junit.jupiter.api.Test
import printscript.CommonPrintScriptRunner
import printscript.PrintscriptRunner
import java.io.File
import java.io.FileNotFoundException
import java.util.*

class PrintscriptRunnerTest {

    private var numberOfLines = 0
    private lateinit var lineBytes: IntArray

    fun data(): Collection<Array<String>> {
        return listOf(
            arrayOf("1.0", "arithmetic-operations"),
            arrayOf("1.0", "arithmetic-operations-decimal"),
            arrayOf("1.0", "simple-declare-assign"),
            arrayOf("1.0", "string-and-number-concat")
        )
    }

    @Test
    fun testWithCounter() {
        val input = MockInputStream("println('hello world');\n", 32 * 1024)
        val inputStreamInput = InputStreamInput(input)
        val runner: PrintscriptRunner = CommonPrintScriptRunner()
        val flow = inputStreamInput.getFlow()
        runner.runExecution(flow, { println(it) }, { "" }, CommonErrorHandler())
    }

    @Test
    @Throws(FileNotFoundException::class)
    fun testPrintStatement() {
        val runner: PrintscriptRunner = CommonPrintScriptRunner()

        val testFile = "src/test/kotlin/print-statement.ps"
        val srcFile = File(testFile)
        val fileInput = LexerFileInput(srcFile)
        runner.runExecution(fileInput.getFlow(), { println(it) }, { "" }, CommonErrorHandler())
    }

    @Test
    @Throws(FileNotFoundException::class)
    fun testWholePrintStatement() {
        data().forEach { (_, directory) ->
            val runner: PrintscriptRunner = CommonPrintScriptRunner()

            val testDirectory = "src/test/kotlin/resources/1.0/$directory/"
            val srcFile = File(testDirectory + "main.ps")
            val fileInput = LexerFileInput(srcFile)

            runner.runExecution(fileInput.getFlow(), { println(it) }, { "" }, CommonErrorHandler())

            println("\n Tested 1.0/$directory \n")
        }
    }
}
