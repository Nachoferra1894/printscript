package interfaces

import java.io.File

import version.Version

interface LinterI {
    fun getLintedCodeCorrection(node: ASTNode, configFile: File,version: Version): String
}
