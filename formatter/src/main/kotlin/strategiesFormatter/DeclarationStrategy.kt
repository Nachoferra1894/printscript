package strategiesFormatter

import configuration.ConfigClasses
import strategiesFormatter.AssignationStrategy.Companion.defineAssignation
import strategiesFormatter.ColonStrategy.Companion.defineColon
import types.VariableDeclarationNode

class DeclarationStrategy {
    companion object {
        fun defineValue(config: ArrayList<ConfigClasses>, declarationNode: VariableDeclarationNode): String {
            val declaration = "let ${declarationNode.getName()}"
            return if (declarationNode.getValue() != null) {
                declaration + defineColon(config) + declarationNode.getType() + defineAssignation(config) + declarationNode.getValue()
            } else {
                declaration + defineColon(config) + declarationNode.getType()
            }
        }
    }
}
