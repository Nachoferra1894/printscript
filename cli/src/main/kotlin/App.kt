import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.file
import input.LexerFileInput
import kotlinx.coroutines.runBlocking
import printscript.CommonPrintScriptRunner
import printscript.PrintscriptRunner
import version.getVersionFromString
import java.io.File

class App : CliktCommand() {
    enum class Operation { Validation, Execution, Formatting, Analyzing }

    private val operation: Operation? by argument(help = "The operation type").enum<Operation>()

    private val sourceFile by argument(help = "Source file path").file()

    private val version by argument(help = "Source file version").optional()

    private val arguments by argument(help = "Operation arguments").optional()

    override fun run() {
        echo("Operation: $operation")
        echo("Source file: ${sourceFile.absolutePath}")

        arguments?.let { echo("Arguments: $it") }
        val v = getVersionFromString(version ?: "v1")
        val runner = CommonPrintScriptRunner(v)
        when (operation) {
            Operation.Validation -> validate(sourceFile.absolutePath, runner)
            Operation.Execution -> execute(sourceFile.absolutePath, runner)
            Operation.Formatting -> format(sourceFile.absolutePath, runner, arguments)
            Operation.Analyzing -> analyze(sourceFile.absolutePath, runner, arguments)
            null -> echo("No operation specified")
        }
    }

    private fun analyze(absolutePath: String, runner: PrintscriptRunner, ruleFileName: String?) {
        if (ruleFileName == null) {
            echo("No arguments specified")
            return
        }
        val lexerInput = LexerFileInput(File(absolutePath))
        runner.runAnalyzing(lexerInput.getFlow(), File(ruleFileName))
    }

    private fun format(absolutePath: String, runner: PrintscriptRunner, ruleFileName: String?) {
        if (ruleFileName == null) {
            echo("No arguments specified")
            return
        }
        val ruleFile = File(ruleFileName)
        val lexerInput = LexerFileInput(File(absolutePath))
        runner.runFormatting(lexerInput.getFlow(), ruleFile)
    }

    private fun execute(absolutePath: String, runner: PrintscriptRunner) {
        echo("exec")
        fun printFunction(output: String) {
            echo(output)
        }
        fun inputFunction(input: String): String {
            echo(input)
            return readln()
        }
        runBlocking {
            val lexerInput = LexerFileInput(File(absolutePath))
            runner.runExecution(lexerInput.getFlow(), ::printFunction, ::inputFunction)
        }
    }

    private fun validate(absolutePath: String, runner: PrintscriptRunner) {
        val lexerInput = LexerFileInput(File(absolutePath))
        runner.runValidation(lexerInput.getFlow())
    }
}

fun main(args: Array<String>) = App().main(args)
