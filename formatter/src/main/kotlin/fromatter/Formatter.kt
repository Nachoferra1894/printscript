package fromatter

import configuration.ReadConfig
import generators.FormatterVisitor
import interfaces.ASTNode
import interfaces.FormatterI
import lexer.exceptions.NoConfigFile
import java.io.File

class Formatter : FormatterI {

    override fun getFormattedCode(node: ASTNode, configFile: File): String {
        val readConfig = ReadConfig()
        val configClasses = readConfig.getJsonDataFromAsset(configFile)
        if (configClasses != null) {
            val formatterVisitor = FormatterVisitor(configClasses)
            node.accept(formatterVisitor)
            return formatterVisitor.getLines()
        }
        throw NoConfigFile("No config file defined, please define it to use the Linter")
    }
}
