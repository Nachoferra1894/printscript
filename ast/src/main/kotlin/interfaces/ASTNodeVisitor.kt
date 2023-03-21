package interfaces

import types.AssignmentExpression
import types.ParentNode
import types.PrintExpression
import types.VariableDeclaration

interface ASTNodeVisitor {
    fun visitDeclaration(variableDeclaration: VariableDeclaration)
    fun visitAssignment(assignmentExpression: AssignmentExpression)
    fun visitPrint(printExpression: PrintExpression)
    fun visitParentNode(parentNode: ParentNode)
}
