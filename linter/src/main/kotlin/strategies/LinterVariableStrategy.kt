package strategies

import configurationLinter.CamelCase
import configurationLinter.ConfigClassesLinter
import configurationLinter.SnakeCase
import configurationLinter.TextCase
import types.VariableDeclarationNode

class LinterVariableStrategy {
    fun checkIdentifierCondition(node: VariableDeclarationNode, configClasses: ArrayList<ConfigClassesLinter>): Boolean {
        val case: TextCase = configClasses.find { it is TextCase } as TextCase
        val name = node.getName()
        return when (case) {
            is CamelCase -> isCamelCase(name)
            is SnakeCase -> isSnakeCase(name)
        }
    }

    fun getIncorrectLine(node: VariableDeclarationNode): String {
        return "Incorrect identifier format [line: " + node.getLine() + " ]"
    }

    private fun isCamelCase(str: String): Boolean {
        val camelCaseRegex = Regex("^[a-z]+([A-Z][a-z]*)*\$")
        return camelCaseRegex.matches(str)
    }

    private fun isSnakeCase(str: String): Boolean {
        val snakeCaseRegex = Regex("^[a-z]+(_[a-z]+)*\$")
        return snakeCaseRegex.matches(str)
    }
}
