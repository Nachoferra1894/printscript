package interfaces

import VariableDeclaration

interface ASTNodeVisitor {
    fun visitDeclaration(variableDeclaration: VariableDeclaration)
    fun visitAssignment(assignmentExpression: AssignmentExpression)
    fun visitExpression(printExpression: PrintExpression)
    fun visitParentNode(parentNode: ParentNode)
}