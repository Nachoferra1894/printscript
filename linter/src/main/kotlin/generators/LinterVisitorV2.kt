package generators

import configuration.ConfigClasses
import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitorV2
import strategies.ExpressionStrategy
import strategies.LinterPrintStrategy
import strategies.LinterVariableStrategy
import types.AssignmentNode
import types.IfNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class LinterVisitorV2(private val configClasses: ArrayList<ConfigClasses>) : ASTNodeVisitorV2 {
    private val lines: ArrayList<String> = ArrayList()

    override fun visitIfNode(ifNode: IfNode) {}

    override fun visitDeclaration(variableDeclaration: VariableDeclarationNode) {
        val strategy = LinterVariableStrategy()
        if (!strategy.checkIdentifierCondition(variableDeclaration, configClasses)) {
            lines.add(strategy.getIncorrectLine(variableDeclaration))
        }
        if (variableDeclaration.getValue() != null) {
            val strategyExpression = ExpressionStrategy()
            if (!strategyExpression.checkContent(variableDeclaration.getValue()!!, configClasses)) {
                lines.add(strategyExpression.getIncorrectLine(variableDeclaration.getValue()!!))
            }
        }
    }

    override fun visitAssignment(assignmentNode: AssignmentNode) {
        val strategyExpression = ExpressionStrategy()
        if (!strategyExpression.checkContent(assignmentNode.value, configClasses)) {
            lines.add(strategyExpression.getIncorrectLine(assignmentNode.value))
        }
    }

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
        val strategy = ExpressionStrategy()
        if (!strategy.checkContent(expressionNode, configClasses)) {
            lines.add(strategy.getIncorrectLine(expressionNode))
        }
        return null
    }

    fun getLines(): String {
        return lines.joinToString("\n")
    }
}
