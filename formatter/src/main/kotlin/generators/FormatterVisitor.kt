package generators

import configuration.ConfigClasses
import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor
import strategiesFormatter.AssigmentStrategy.Companion.defineValue
import strategiesFormatter.DeclarationStrategy.Companion.defineValue
import strategiesFormatter.PrintlnStrategy.Companion.defineValue
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class FormatterVisitor(private val configClasses: ArrayList<ConfigClasses>) : ASTNodeVisitor {
    private val lines: ArrayList<String> = ArrayList()

    override fun visitDeclaration(variableDeclaration: VariableDeclarationNode) {
        val value = defineValue(this.configClasses, variableDeclaration)
        lines.add("$value;")
    }

    override fun visitAssignment(assignmentNode: AssignmentNode) {
        lines.add("${defineValue(this.configClasses, assignmentNode)};")
    }

    override fun visitPrint(printNode: PrintNode) {
        lines.add("${defineValue(this.configClasses, printNode)};")
    }

    override fun visitParentNode(parentNode: ParentNode) {
        parentNode.getChildren().forEach { it.accept(this) }
    }

    override fun visitExpressionNode(expressionNode: Expression): ASTNode? {
        lines.add("$expressionNode;")
        return null // TODO delete when interpreterVisitor is fixed
    }

    fun getLines(): String {
        return lines.joinToString("\n")
    }
}
