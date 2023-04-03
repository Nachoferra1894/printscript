package implementation

import interfaces.Printer
import interfaces.ASTNodeVisitor
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class InterpreterVisitor(
    val map: InterpreterMap,
    private val printer : Printer
) : ASTNodeVisitor {

    override fun visitDeclaration(variableDeclaration: VariableDeclarationNode) : VariableDeclarationNode {
        return variableDeclaration
    }

    override fun visitAssignment(assignmentNode: AssignmentNode) : AssignmentNode {
        TODO("Not yet implemented")

    }

    override fun visitPrint(printNode: PrintNode) : PrintNode {
        TODO("Not yet implemented")
    }

    override fun visitParentNode(parentNode: ParentNode) : ParentNode {
        TODO("Not yet implemented")
    }

}