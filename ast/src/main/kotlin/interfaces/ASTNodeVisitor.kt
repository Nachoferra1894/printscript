package interfaces

import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode
import expresions.Expression

interface ASTNodeVisitor {
    fun visitDeclaration(variableDeclaration: VariableDeclarationNode)
    fun visitAssignment(assignmentNode: AssignmentNode)
    fun visitPrint(printNode: PrintNode)
    fun visitParentNode(parentNode: ParentNode)
    fun visitExpressionNode(expressionNode: Expression)
}
