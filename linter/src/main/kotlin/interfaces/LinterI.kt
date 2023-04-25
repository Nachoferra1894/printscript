package interfaces

import version.Version
import java.io.File

interface LinterI {
    fun getLintedCodeCorrection(node: ASTNode, configFile: File, version: Version): String
}
