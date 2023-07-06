import input.InputStreamInput
import input.LexerFileInput
import interpreterUtils.PrinterImpl
import interpreterUtils.ReadInputImpl
import org.junit.jupiter.api.Test
import printscript.CommonPrintScriptRunner
import printscript.PrintscriptRunner
import util.CommonErrorHandler
import version.V1
import version.getVersionFromString
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
            arrayOf("1.0", "string-and-number-concat"),
            arrayOf("1.1", "if-statement-true"),
            arrayOf("1.1", "if-statement-false"),
            arrayOf("1.1", "else-statement-true"),
            arrayOf("1.1", "else-statement-false"),
            arrayOf("1.1", "read-input")
        )
    }

    @Test
    fun testWithCounter() {
        val input = MockInputStream("println('hello world');\n", 32 * 1024)
        val inputStreamInput = InputStreamInput(input)
        val runner: PrintscriptRunner = CommonPrintScriptRunner(PrinterImpl())
        val flow = inputStreamInput.getFlow()
        runner.runExecution(flow, CommonErrorHandler())
    }

    @Test
    @Throws(FileNotFoundException::class)
    fun testPrintStatement() {
        val runner: PrintscriptRunner = CommonPrintScriptRunner(PrinterImpl(), V1())
        val errorHandler = CommonErrorHandler()

        val testFile = "src/test/kotlin/print-statement.ps"
        val srcFile = File(testFile)
        val fileInput = LexerFileInput(srcFile)
        runner.runExecution(fileInput.getFlow(), errorHandler)
        assert(errorHandler.getErrors().isNotEmpty())
    }

    @Test
    @Throws(FileNotFoundException::class)
    fun testWholePrintStatement() {
        data().forEach { (version, directory) ->
            val v = getVersionFromString(version)
            val runner: PrintscriptRunner = CommonPrintScriptRunner(PrinterImpl(), v, ReadInputImpl())

            val testDirectory = "src/test/kotlin/resources/$version/$directory/"
            val srcFile = File(testDirectory + "main.ps")
            val fileInput = LexerFileInput(srcFile)

            runner.runExecution(fileInput.getFlow(), CommonErrorHandler())

            println("\n Tested $version/$directory \n")
        }
    }
}
