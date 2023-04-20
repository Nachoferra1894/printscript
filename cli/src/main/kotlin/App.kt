import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.file
import kotlinx.coroutines.runBlocking
import printscript.CommonPrintScriptRunner
import java.io.File

class App : CliktCommand() {
    enum class Operation { Validation, Execution, Formatting, Analyzing }

    private val runner = CommonPrintScriptRunner()

    private val operation: Operation? by argument(help = "The operation type").enum<Operation>()

    private val sourceFile by argument(help = "Source file path").file()

    private val version by argument(help = "Source file version").optional()

    private val arguments by argument(help = "Operation arguments").optional()

    override fun run() {
        echo("Operation: $operation")
        echo("Source file: ${sourceFile.absolutePath}")

        arguments?.let { echo("Arguments: $it") }
        val v = version ?: "v1"
        when (operation) {
            Operation.Validation -> validate(sourceFile.absolutePath, v, arguments)
            Operation.Execution -> execute(sourceFile.absolutePath, v, arguments)
            Operation.Formatting -> format(sourceFile.absolutePath, v, arguments)
            Operation.Analyzing -> analyze(sourceFile.absolutePath, v, arguments)
            null -> echo("No operation specified")
        }
    }

    private fun analyze(absolutePath: String, version: String, ruleFileName: String?) {
        format(absolutePath, version, ruleFileName)
    }

    private fun format(absolutePath: String, version: String, ruleFileName: String?) {
        if (ruleFileName == null) {
            echo("No arguments specified")
            return
        }
        val file = File(absolutePath)
        val ruleFile = File(ruleFileName)
        runner.runFormatting(file, version, ruleFile)
    }

    private fun execute(absolutePath: String, version: String, arguments: String?) {
        echo("exec")
        fun printFunction(output: String) {
            echo(output)
        }
        runBlocking {
            val file = File(absolutePath)
            runner.runExecution(file, version, ::printFunction)
        }
    }

    private fun validate(absolutePath: String, version: String, arguments: String?) {
        val file = File(absolutePath)
        runner.runValidation(file, version)
    }
}

fun main(args: Array<String>) = App().main(args)
