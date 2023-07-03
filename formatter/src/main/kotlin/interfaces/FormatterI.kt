package interfaces

import configuration.ConfigClasses
import version.Version
import java.io.File

interface FormatterI {
    fun getFormattedCode(node: ASTNode, version: Version, configFile: File? = null): String

    fun getFormattedCode(node: ASTNode, version: Version, configClasses: ArrayList<ConfigClasses>): String
}
