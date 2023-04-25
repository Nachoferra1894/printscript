package generators

import configuration.ConfigClasses
import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitorV2
import strategiesFormatter.AssigmentStrategy
import strategiesFormatter.DeclarationStrategy.Companion.defineValueV2
import strategiesFormatter.PrintlnStrategy
import types.AssignmentNode
import types.IfNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class FormatterVisitorV2(private val configClasses: ArrayList<ConfigClasses>) : ASTNodeVisitorV2 {
    private val lines: ArrayList<String> = ArrayList()

    override fun visitIfNode(ifNode: IfNode) {
        TODO("Not yet implemented")
    }

    override fun visitDeclaration(variableDeclaration: VariableDeclarationNode) {
        val value = defineValueV2(this.configClasses, variableDeclaration)
        lines.add("$value;")
    }

    override fun visitAssignment(assignmentNode: AssignmentNode) {
        lines.add("${AssigmentStrategy.defineValue(this.configClasses, assignmentNode)};")
    }

    override fun visitPrint(printNode: PrintNode) {
        lines.add("${PrintlnStrategy.defineValue(this.configClasses, printNode)};")
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
