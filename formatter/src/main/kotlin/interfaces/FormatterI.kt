package interfaces

import version.Version

interface FormatterI {
    fun getFormattedCode(node: ASTNode, version: Version): String
}
