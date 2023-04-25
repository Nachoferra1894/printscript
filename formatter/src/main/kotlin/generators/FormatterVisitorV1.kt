package generators

import configuration.ConfigClasses
import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitorV1
import strategiesFormatter.AssigmentStrategy.Companion.defineValue
import strategiesFormatter.DeclarationStrategy.Companion.defineValueV1
import strategiesFormatter.PrintlnStrategy.Companion.defineValue
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class FormatterVisitorV1(private val configClasses: ArrayList<ConfigClasses>) : ASTNodeVisitorV1 {
    private val lines: ArrayList<String> = ArrayList()

    override fun visitDeclaration(variableDeclaration: VariableDeclarationNode) {
        val value = defineValueV1(this.configClasses, variableDeclaration)
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
