package strategiesFormatter

import configuration.ConfigClasses
import configuration.SpaceIndexedForIf
import expresions.Expression
import interfaces.ASTNode
import strategiesFormatter.AssigmentStrategy.Companion.defineValue
import strategiesFormatter.DeclarationStrategy.Companion.defineValueV2
import strategiesFormatter.PrintlnStrategy.Companion.defineValue
import types.AssignmentNode
import types.IfNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class IfStrategy {
    companion object {
        fun defineValue(config: ArrayList<ConfigClasses>, ifNode: IfNode): String {
            val ifLine = defineIfLine(ifNode)
            if (ifNode.getFalsyNode() != null) {
                return ifLine + defineInside(ifNode.getTruthyNode()!!, config) + "}" + defineElseLine() + defineInside(ifNode.getFalsyNode()!!, config) + "}"
            }
            return ifLine + defineInside(ifNode.getTruthyNode()!!, config) + "}"
        }

        private fun defineElseLine(): String {
            return "else{\n"
        }

        private fun defineIfLine(ifNode: IfNode): String {
            return "if (${ifNode.getCondition()!!}) {\n"
        }

        private fun defineInside(node: ASTNode, config: ArrayList<ConfigClasses>): String {
            val valueIndexed = config.filterIsInstance<SpaceIndexedForIf>()
            if (valueIndexed.isNotEmpty()) {
                val value = valueIndexed[0].getLines()
                val preLine = definePreLine(value)
                return defineForDifferentTypesOfNodes(preLine, node, config)
            }
            return defineForDifferentTypesOfNodes("", node, config) + "\n"
        }

        private fun definePreLine(value: Int): String {
            return " ".repeat(value)
        }

        private fun defineForDifferentTypesOfNodes(preLine: String, node: ASTNode, config: ArrayList<ConfigClasses>): String {
            if (node is ParentNode) {
                var define = ""
                node.getChildren().forEach {
                    define += defineForDifferentTypesOfNodes(preLine, it, config) + "\n"
                }
                return define
            }
            if (node is VariableDeclarationNode) {
                return preLine + defineValueV2(config, node) + ";"
            }
            if (node is AssignmentNode) {
                return preLine + defineValue(config, node) + ";"
            }
            if (node is PrintNode) {
                return preLine + defineValue(config, node) + ";"
            }
            if (node is Expression) {
                return "$preLine$node;"
            }
            return ""
        }
    }
}
