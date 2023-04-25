package interfaces

import version.Version

interface LinterI {
    fun getLinteredCodeCorrection(node: ASTNode, version: Version): String
}
