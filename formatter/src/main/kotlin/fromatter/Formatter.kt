package fromatter

import configuration.ReadConfig
import generators.FormatterVisitor
import interfaces.ASTNode
import interfaces.FormatterI
import lexer.exceptions.NoConfigFile

class Formatter : FormatterI {

    override fun getFormattedCode(node: ASTNode): String {
        val readConfig = ReadConfig()
        val configClasses = readConfig.getJsonDataFromAsset()
        if (configClasses != null) {
            val formatterVisitor = FormatterVisitor(configClasses)
            node.accept(formatterVisitor)
            return formatterVisitor.getLines()
        }
        throw NoConfigFile("No config file defined, please define it to use the Linter")
    }
}
