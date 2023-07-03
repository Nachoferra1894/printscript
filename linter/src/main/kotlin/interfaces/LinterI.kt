package interfaces

import configurationLinter.ConfigClassesLinter
import version.Version
import java.io.File

interface LinterI {
    fun getLintedCodeCorrection(node: ASTNode, version: Version, configFile: File? = null): String

    fun getLintedCodeCorrection(node: ASTNode, version: Version, configClasses: ArrayList<ConfigClassesLinter>): String
}
