package strategies

import types.VariableDeclarationNode

class VariableStrategy {
    var condition = "camelCase"

    fun checkIdentifierCondition(node: VariableDeclarationNode): Boolean{
        val name = node.getName()
        if(condition == "camelCase"){
             return isCamelCase(name)
        }
        if (condition == "snakeCase"){
            return isSnakeCase(name)
        }
        return true
    }

    fun getIncorrectLine(node: VariableDeclarationNode) : String {
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