package strategies

import interfaces.ASTNode
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class NodeStrategy {
    companion object {
        fun assigmentStrategy(astNode: ASTNode): Boolean {
            return astNode is AssignmentNode
        }

        fun parentStrategy(astNode: ASTNode): Boolean {
            return astNode is ParentNode
        }

        fun printerStrategy(astNode: ASTNode): Boolean {
            return astNode is PrintNode
        }

        fun variableDeclarationStrategy(astNode: ASTNode): Boolean {
            return astNode is VariableDeclarationNode
        }
    }
}
