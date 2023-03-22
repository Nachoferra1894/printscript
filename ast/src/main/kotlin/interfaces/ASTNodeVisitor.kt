package interfaces

import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclaration

interface ASTNodeVisitor {
    fun visitDeclaration(variableDeclaration: VariableDeclaration)
    fun visitAssignment(assignmentNode: AssignmentNode)
    fun visitPrint(printNode: PrintNode)
    fun visitParentNode(parentNode: ParentNode)
}
