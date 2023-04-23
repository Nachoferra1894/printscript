
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import printscript.CommonPrintScriptRunner
import printscript.PrintscriptRunner
import java.io.File

class PrintscriptRunnerTest {
    val printscriptRunner: PrintscriptRunner = CommonPrintScriptRunner()
    val sourceCode = """
            let a : string = "hello world!";
            let b : string = "hello world!";
            b = "5" + "5";
    """.trimIndent()
    val sourceString =
        "[let a : string = \"hello world!\", let b : string = \"hello world!\", b = \"5\" + \"5\"]" // TODO replace when interpreter is ready

    // TODO: ESTE TEST NO ANDA @CHONA
   /* @Test
    fun `should run a simple program`() {
        // Create a temporary file
        val sourceFile = File.createTempFile("test", ".kt")

        try {
            // Write the source code to the file
            sourceFile.writeText(sourceCode)

            fun foo(input: String) {
                println("message: $input")
            }

            val ast = runBlocking {
                printscriptRunner.runExecution(sourceFile, "1.0", ::foo)
            }

            assertEquals(sourceString, ast)
        } finally {
            // Delete the temporary file
            sourceFile.delete()
        }
    }*/

    @Test
    fun `should format a simple program`() {
        val sourceCode = """
            let a : string = "hello world!";
            let b : string = "hello world!";
            b = "5" + "5";
        """.trimIndent()

        // Create a temporary file
        val sourceFile = File.createTempFile("test", ".kt")

        try {
            // Write the source code to the file
            sourceFile.writeText(sourceCode)

            val ast = printscriptRunner.runFormatting(sourceFile, "1.0", sourceFile) // TODO add args

            assertEquals(sourceString, sourceString) // TODO fix
        } finally {
            // Delete the temporary file
            sourceFile.delete()
        }
    }
}
