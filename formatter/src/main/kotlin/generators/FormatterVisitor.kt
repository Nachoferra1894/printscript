package generators

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor
import types.AssignmentNode
import types.IfNode
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
        lines.add("println($planeValue);")
    }

    override fun visitParentNode(parentNode: ParentNode) {
        parentNode.getChildren().forEach { it.accept(this) }
    }

    override fun visitExpressionNode(expressionNode: Expression): ASTNode? {
        lines.add("$expressionNode;")
        return null // TODO delete when interpreterVisitor is fixed
    }

    override fun visitIfNode(ifNode: IfNode) {
        TODO("Not yet implemented")
    }

    fun getLines(): String {
        return lines.joinToString("\n")
    }
}
