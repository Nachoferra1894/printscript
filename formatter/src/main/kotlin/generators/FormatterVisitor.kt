package generators

import expresions.Expression
import interfaces.ASTNodeVisitor
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class FormatterVisitor : ASTNodeVisitor {
    private val lines: ArrayList<String> = ArrayList()

    override fun visitDeclaration(variableDeclaration: VariableDeclarationNode) {
        val planeValue = variableDeclaration.toString()
        lines.add("$planeValue;")
    }

    override fun visitAssignment(assignmentNode: AssignmentNode) {
        val name = assignmentNode.name
        val planeValue = assignmentNode.value.toString()
        lines.add("$name = $planeValue;")
    }

    override fun visitPrint(printNode: PrintNode) {
        val planeValue = printNode.content.toString()
        lines.add("print($planeValue);")
    }

    override fun visitParentNode(parentNode: ParentNode) {
        parentNode.getChildren().forEach { it.accept(this) }
    }

    override fun visitExpressionNode(expressionNode: Expression) {
        lines.add("${expressionNode};")
    }

    fun getLines(): String {
        return lines.joinToString("\n")
    }
}
