package interfaces

import expresions.Expression
import types.AssignmentNode
import types.IfNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

interface ASTNodeVisitor {
    fun visitDeclaration(variableDeclaration: VariableDeclarationNode)
    fun visitAssignment(assignmentNode: AssignmentNode)
    fun visitPrint(printNode: PrintNode)
    fun visitParentNode(parentNode: ParentNode)
    fun visitExpressionNode(expressionNode: Expression): ASTNode?
    fun visitIfNode(ifNode: IfNode)
}
