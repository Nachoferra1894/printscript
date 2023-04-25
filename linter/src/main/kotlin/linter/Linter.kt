package linter

import configuration.ReadConfig
import generators.LinterVisitor
import interfaces.ASTNode
import interfaces.LinterI
import lexer.exceptions.NoConfigFile
import java.io.File

class Linter : LinterI {
    override fun getLintedCodeCorrection(node: ASTNode, configFile: File): String {
        val readConfig = ReadConfig()
        val configClasses = readConfig.getJsonDataFromAsset(configFile)
        if (configClasses != null) {
            val formatterVisitor = LinterVisitor(configClasses)
            node.accept(formatterVisitor)
            return formatterVisitor.getLines()
        }
        throw NoConfigFile("No config file defined, please define it to use the Linter")
    }
}
