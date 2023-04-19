package linter

import generators.LinterVisitor
import interfaces.ASTNode
import interfaces.LinterI

class Linter : LinterI {
    override fun getLinteredCodeCorrection(node: ASTNode): String {
        val formatterVisitor = LinterVisitor()
        node.accept(formatterVisitor)
        return formatterVisitor.getLines()
    }
}
