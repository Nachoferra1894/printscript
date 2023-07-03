package linter

import configurationLinter.ConfigClassesLinter
import configurationLinter.ReadConfigLinter
import generators.LinterVisitorV1
import generators.LinterVisitorV2
import interfaces.ASTNode
import interfaces.LinterI
import lexer.exceptions.NoConfigFile
import version.V1
import version.V2
import version.Version
import java.io.File

class Linter : LinterI {
    override fun getLintedCodeCorrection(node: ASTNode, configFile: File, version: Version): String {
        val readConfig = ReadConfigLinter()
        val configClasses = readConfig.getJsonDataFromAsset(configFile)
        if (configClasses != null) {
            return defineLine(configClasses, node, version)
        }
        throw NoConfigFile("No config file defined, please define it to use the Linter")
    }

    override fun getLintedCodeCorrection(
        node: ASTNode,
        configClasses: ArrayList<ConfigClassesLinter>,
        version: Version
    ): String {
        return defineLine(configClasses, node, version)
    }

    private fun defineLine(
        configClasses: ArrayList<ConfigClassesLinter>,
        node: ASTNode,
        version: Version
    ): String {
        return when (version) {
            is V1 -> defineLineV1(configClasses, node)
            is V2 -> defineLineV2(configClasses, node)
        }
    }

    private fun defineLineV1(configClasses: ArrayList<ConfigClassesLinter>, node: ASTNode): String {
        val formatterVisitor = LinterVisitorV1(configClasses)
        node.accept(formatterVisitor)
        return formatterVisitor.getLines()
    }

    private fun defineLineV2(configClasses: ArrayList<ConfigClassesLinter>, node: ASTNode): String {
        val formatterVisitor = LinterVisitorV2(configClasses)
        node.accept(formatterVisitor)
        return formatterVisitor.getLines()
    }
}
