package generators

import configuration.ConfigClasses
import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor
import strategies.LinterPrintStrategy
import strategies.LinterVariableStrategy
import types.AssignmentNode
import types.IfNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class LinterVisitor(private val configClasses: ArrayList<ConfigClasses>) : ASTNodeVisitor {
    private val lines: ArrayList<String> = ArrayList()

    override fun visitDeclaration(variableDeclaration: VariableDeclarationNode) {
        val strategy = LinterVariableStrategy()
        if (!strategy.checkIdentifierCondition(variableDeclaration, configClasses)) {
            lines.add(strategy.getIncorrectLine(variableDeclaration))
        }
    }

    override fun visitAssignment(assignmentNode: AssignmentNode) {}

    override fun visitPrint(printNode: PrintNode) {
        val strategy = LinterPrintStrategy()
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

    override fun visitIfNode(ifNode: IfNode) {
        TODO("Not yet implemented")
    }

    fun getLines(): String {
        return lines.joinToString("\n")
    }
}
