package linter

import configurationLinter.ConfigClasses
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

    private fun defineLine(
        configClasses: ArrayList<ConfigClasses>,
        node: ASTNode,
        version: Version
    ): String {
        return when (version) {
            is V1 -> defineLineV1(configClasses, node)
            is V2 -> defineLineV2(configClasses, node)
        }
    }

    private fun defineLineV1(configClasses: ArrayList<ConfigClasses>, node: ASTNode): String {
        val formatterVisitor = LinterVisitorV1(configClasses)
        node.accept(formatterVisitor)
        return formatterVisitor.getLines()
    }

    private fun defineLineV2(configClasses: ArrayList<ConfigClasses>, node: ASTNode): String {
        val formatterVisitor = LinterVisitorV2(configClasses)
        node.accept(formatterVisitor)
        return formatterVisitor.getLines()
    }
}
