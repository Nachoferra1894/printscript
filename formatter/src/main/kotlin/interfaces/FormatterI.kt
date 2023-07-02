package interfaces

import java.io.File

interface FormatterI {
    fun getFormattedCode(node: ASTNode, configFile: File): String
}
