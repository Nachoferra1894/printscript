package fromatter

import configuration.ConfigClasses
import configuration.ReadConfig
import generators.FormatterVisitorV1
import interfaces.ASTNode
import interfaces.FormatterI
import lexer.exceptions.NoConfigFile
import version.V1
import version.V2
import version.Version
import java.util.ArrayList

class Formatter : FormatterI {

    override fun getFormattedCode(node: ASTNode, version: Version): String {
        val readConfig = ReadConfig()
        val configClasses = readConfig.getJsonDataFromAsset()
        if (configClasses != null) {
            return defineLines(node, configClasses, version)
        }
        throw NoConfigFile("No config file defined, please define it to use the Linter")
    }

    private fun defineLines(astNode: ASTNode, configClasses: ArrayList<ConfigClasses>, version: Version): String {
        return when (version) {
            is V1 -> defineLinesForV1(astNode, configClasses)
            is V2 -> defineLinesForV2(astNode, configClasses)
        }
    }

    private fun defineLinesForV1(node: ASTNode, configClasses: ArrayList<ConfigClasses>): String {
        val formatterVisitor = FormatterVisitorV1(configClasses)
        node.accept(formatterVisitor)
        return formatterVisitor.getLines()
    }

    private fun defineLinesForV2(node: ASTNode, configClasses: ArrayList<ConfigClasses>): String {
        val formatterVisitor = FormatterVisitorV1(configClasses)
        node.accept(formatterVisitor)
        return formatterVisitor.getLines()
    }
}
