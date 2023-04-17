import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.file

class App : CliktCommand() {
    enum class Operation { Validation, Execution, Formatting, Analyzing }

    private val operation: Operation? by option(help = "The operation type").enum<Operation>()

    private val sourceFile by argument(help = "Source file path").file()

    private val version by argument(help = "Source file version").optional()

    private val arguments by argument(help = "Operation arguments").optional()

    override fun run() {
        echo("Operation: $operation")
        echo("Source file: ${sourceFile.absolutePath}")
        version?.let { echo("Version: $it") }
        arguments?.let { echo("Arguments: $it") }
    }
}

fun main(args: Array<String>) = App().main(args)
