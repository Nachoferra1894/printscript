package generators

import configuration.ConfigClasses
import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor
import strategies.PrintStrategy
import strategies.VariableStrategy
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class LinterVisitor(private val configClasses: ArrayList<ConfigClasses>) : ASTNodeVisitor {
    private val lines: ArrayList<String> = ArrayList()

    override fun visitDeclaration(variableDeclaration: VariableDeclarationNode) {
        val strategy = VariableStrategy()
        if (!strategy.checkIdentifierCondition(variableDeclaration, configClasses)) {
            lines.add(strategy.getIncorrectLine(variableDeclaration))
        }
    }

    override fun visitAssignment(assignmentNode: AssignmentNode) {}

    override fun visitPrint(printNode: PrintNode) {
        val strategy = PrintStrategy()
        if (!strategy.checkContent(printNode, configClasses)) {
            lines.add(strategy.getIncorrectLine(printNode))
        }
    }

    override fun visitParentNode(parentNode: ParentNode) {
        parentNode.getChildren().forEach { it.accept(this) }
    }

    override fun visitExpressionNode(expressionNode: Expression): ASTNode? {
        return null
    }

    fun getLines(): String {
        return lines.joinToString("\n")
    }
}
