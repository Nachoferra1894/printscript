package interfaces

import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

interface ASTNodeVisitor {
    fun visitDeclaration(variableDeclaration: VariableDeclarationNode) : ASTNode
    fun visitAssignment(assignmentNode: AssignmentNode) : ASTNode
    fun visitPrint(printNode: PrintNode) : ASTNode
    fun visitParentNode(parentNode: ParentNode) : ASTNode
}
