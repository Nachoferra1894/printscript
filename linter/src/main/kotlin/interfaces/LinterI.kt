package interfaces

import configurationLinter.ConfigClassesLinter
import version.Version
import java.io.File

interface LinterI {
    fun getLintedCodeCorrection(node: ASTNode, configFile: File, version: Version): String

    fun getLintedCodeCorrection(node: ASTNode, configClasses: ArrayList<ConfigClassesLinter>, version: Version): String
}
