package implementation

import expresions.Expression
import expresions.types.Operation
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
        TODO("Not yet implemented")
    }

    override fun visitAssignment(assignmentNode: AssignmentNode) : AssignmentNode {
        TODO("Not yet implemented")
    }

    override fun visitExpressionNode(expressionNode: Expression) {
        TODO("Not yet implemented")
    }

    override fun visitPrint(printNode: PrintNode) : PrintNode {
        TODO("Not yet implemented")
    }

    override fun visitParentNode(parentNode: ParentNode) : ParentNode {
        TODO("Not yet implemented")
    }

}