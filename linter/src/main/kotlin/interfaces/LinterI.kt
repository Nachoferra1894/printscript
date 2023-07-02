package interfaces

import java.io.File

interface LinterI {
    fun getLintedCodeCorrection(node: ASTNode, configFile: File): String
}
