package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitorCommon

class PrintNode(val content: Expression, private val line: Int = 0) : ASTNode {
    override fun getLine(): Int {
        return line
    }

    override fun accept(visitor: ASTNodeVisitorCommon) {
        visitor.visitPrint(this)
    }

    override fun toString(): String {
        return "println($content)"
    }
}
