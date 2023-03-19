package interfaces

import VariableDeclaration

interface ASTNodeVisitor {
    fun visitNode(variableDeclaration: VariableDeclaration)
    fun visitNode(assignmentExpression: AssignmentExpression)
    fun visitNode(printExpression: PrintExpression)
    fun visitNode(parentNode: ParentNode)
}