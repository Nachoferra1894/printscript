package linter

import configuration.ReadConfig
import generators.LinterVisitor
import interfaces.ASTNode
import interfaces.LinterI
import lexer.exceptions.NoConfigFile

class Linter : LinterI {
    override fun getLinteredCodeCorrection(node: ASTNode): String {
        val readConfig = ReadConfig()
        val configClasses = readConfig.getJsonDataFromAsset()
        if (configClasses != null) {
            val formatterVisitor = LinterVisitor(configClasses)
            node.accept(formatterVisitor)
            return formatterVisitor.getLines()
        }
        throw NoConfigFile("No config file defined, please define it to use the Linter")
    }
}
