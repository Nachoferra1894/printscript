package strategiesFormatter

import configuration.ConfigClasses
import strategiesFormatter.AssignationStrategy.Companion.defineAssignation
import strategiesFormatter.ColonStrategy.Companion.defineColon
import types.VariableDeclarationNode

class DeclarationStrategy {
    companion object {
        fun defineValueV1(config: ArrayList<ConfigClasses>, declarationNode: VariableDeclarationNode): String {
            val declaration = "let ${declarationNode.getName()}"
            return defineLine(declarationNode, declaration, config)
        }

        fun defineValueV2(config: ArrayList<ConfigClasses>, declarationNode: VariableDeclarationNode): String {
            val mutable = if (declarationNode.isMutable()) "let" else "const"
            val declaration = "$mutable ${declarationNode.getName()}"
            return defineLine(declarationNode, declaration, config)
        }

        private fun defineLine(
            declarationNode: VariableDeclarationNode,
            declaration: String,
            config: ArrayList<ConfigClasses>
        ): String {
            return if (declarationNode.getValue() != null) {
                declaration + defineColon(config) + declarationNode.getType() + defineAssignation(config) + declarationNode.getValue()
            } else {
                declaration + defineColon(config) + declarationNode.getType()
            }
        }
    }
}
