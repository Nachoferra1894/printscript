package strategies

import configuration.ConfigClasses
import configuration.Space
import configuration.SpaceAfterColon
import configuration.SpaceBeforeColon
import kotlinx.coroutines.delay
import types.VariableDeclarationNode

class DeclarationStrategy {
    companion object {
        fun defineValue(config: ArrayList<ConfigClasses>, declarationNode : VariableDeclarationNode) : String {
            val declaration = "let ${declarationNode.getName()}"
            return declaration + defineValueWithSpace(config, declarationNode, declaration)
        }

        private fun defineValueWithSpace(config: ArrayList<ConfigClasses>, declarationNode: VariableDeclarationNode, declaration: String): String {
            var decl = declaration
            val spaceBeforeColon = config.filterIsInstance<SpaceBeforeColon>()
            if(spaceBeforeColon.get(0) != null){
                decl += " :"
            }
            val spaceAfterColon = config.filterIsInstance<SpaceAfterColon>()
            if(spaceAfterColon[0] != null){
                decl += " "
            }
            return decl
        }
    }

}