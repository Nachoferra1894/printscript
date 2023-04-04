package fromatter

import generators.FormatterVisitor
import interfaces.ASTNode
import interfaces.FormatterI

class Formatter : FormatterI {

    override fun getFormattedCode(node: ASTNode): String {
        val formatterVisitor = FormatterVisitor()
        node.accept(formatterVisitor)
        return formatterVisitor.getLines()
    }
}
