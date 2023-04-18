package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class PrintNode(val content: Expression, private val line: Int) : ASTNode {
    override fun getLine(): Int {
        return line
    }

    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitPrint(this)
    }

    override fun toString(): String {
        return "println($content)"
    }
}
