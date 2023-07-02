import linter.Linter
import version.V1
import java.io.File

fun main() {
    val linter = Linter()
    linter.getLintedCodeCorrection(
        node,
        File("/Users/micaeladominguez/faculty/ing-sis/snippet/printscript/linter/src/main/kotlin/configuration/configLinter.json"),
        V1()
    )

}