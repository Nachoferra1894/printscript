package interfaces

import configuration.ConfigClasses
import version.Version
import java.io.File

interface FormatterI {
    fun getFormattedCode(node: ASTNode, configFile: File, version: Version): String

   fun getFormattedCode(node: ASTNode, configClasses: ArrayList<ConfigClasses>, version: Version ) : String
}
