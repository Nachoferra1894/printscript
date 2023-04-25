package interfaces

import version.Version
import java.io.File

interface FormatterI {
    fun getFormattedCode(node: ASTNode, configFile: File, version: Version): String
}
