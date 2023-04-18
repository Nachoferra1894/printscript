import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import printscript.CommonPrintScriptRunner
import printscript.PrintscriptRunner
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

class PrintscriptRunnerTest {
    val printscriptRunner: PrintscriptRunner = CommonPrintScriptRunner()

    @Test
    fun `should run a simple program`() {
        val sourceCode = """
            let a: string = "hello world!";
            let b: string = "hello world!";
            b = a + "5";
        """.trimIndent()
        val sourceString =
            "[let a: string = \"hello world!\", let b: string = \"hello world!\", b = a + \"5\"]" // TODO replace when interpreter is ready

        // Create a temporary file
        val sourceFile = File.createTempFile("test", ".kt")

        try {
            // Write the source code to the file
            sourceFile.writeText(sourceCode)

            val ast = runBlocking {
                printscriptRunner.runExecution(sourceFile, "1.0")
            }

            assertEquals(sourceString, ast)
        } finally {
            // Delete the temporary file
            sourceFile.delete()
        }
    }
}
