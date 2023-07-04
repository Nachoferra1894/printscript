package implementation.v2

import PrototypeType
import expresions.Expression
import expresions.Operator
import expresions.types.Operation
import expresions.types.ReadInputExp
import expresions.types.Variable
import interfaces.ASTNodeVisitorV2
import interpreterUtils.Printer
import interpreterUtils.ReadInput
import types.AssignmentNode
import types.IfNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class InterpreterVisitorV2(
    val map: InterpreterMapV2,
    private val printer: Printer,
    private val readInput: ReadInput
) : ASTNodeVisitorV2 {

    override fun visitDeclaration(variableDeclaration: VariableDeclarationNode) {
        val name = variableDeclaration.getName()
        val type = variableDeclaration.getType()
        val isMutable = variableDeclaration.isMutable()

        if (!map.exist(name)) {
            map.put(name, ValueAndTypeV2(null, type, isMutable))
        }else{
            throw Exception("Variable $name already declared! Can't declare the same variable 2 times!")
        }

        if (variableDeclaration.getValue() != null) {
            val literal = visitExpressionNode(variableDeclaration.getValue()!!)
            map.put(name, ValueAndTypeV2(literal.getValue(), type, isMutable))
        }
    }

    override fun visitAssignment(assignmentNode: AssignmentNode) {
        val variableName = assignmentNode.name
        if (map.exist(variableName)) {
            if (map.getValue(variableName).isMutable) {
                val variableType: String = map.getValue(variableName).type
                var literal: Expression = assignmentNode.value
                if (literal is Operation || literal is ReadInputExp) {
                    literal = visitExpressionNode(assignmentNode.value)
                }
                if (literal is Variable && variableType == getTypeFromPrototype(literal.getType())) {
                    map.put(variableName, ValueAndTypeV2(literal.getValue(), variableType, true))
                } else {
                    throw Exception("Type Error!")
                }
            } else {
                throw Exception("$variableName is not mutable")
            }
        } else {
            throw Exception("The variable: $variableName does not exist!")
        }
    }

    private fun getTypeFromPrototype(literal: PrototypeType): String {
        return literal.toString()
    }

    private fun getPrototypeFromType(type: String): PrototypeType {
        return when (type) {
            "string" -> {
                PrototypeType.STRING
            }

            "number" -> {
                PrototypeType.NUMBER
            }

            "boolean" -> {
                PrototypeType.BOOLEAN
            }

            else -> {
                throw Exception("Type not exists!")
            }
        }
    }

    private fun setPrototypeType(value: String): PrototypeType {
        if (value == "true" || value == "false") {
            return PrototypeType.BOOLEAN
        }
        if (isNumber(value)) {
            return PrototypeType.NUMBER
        }
        return PrototypeType.STRING
    }

    fun isNumber(numero: String): Boolean {
        val regex = Regex("^[0-9]+([,.][0-9]+)?$")
        return regex.matches(numero)
    }
    override fun visitExpressionNode(expressionNode: Expression): Variable {
        if (expressionNode is Variable) {
            if (expressionNode.getType() == PrototypeType.IDENTIFIER) {
                val variableVT = map.getValue(expressionNode.getValue())
                return Variable(
                    variableVT.value.toString(),
                    getPrototypeFromType(variableVT.type),
                    expressionNode.getLine()
                )
            }
            return expressionNode
        }
        if (expressionNode is ReadInputExp) {
            val expression = expressionNode.expression
            if (expression is Variable) {
                if (expression.getType() != PrototypeType.STRING) throw Exception("Read input message should be a string")
                val value = readInput.read(expression.getValue())
                val valueType = setPrototypeType(value)
                return Variable(value, valueType)
            } else {
                throw Exception("Invalid message for read input")
            }
        }
        if (expressionNode is Operation) {
            var l = expressionNode.getL()
            val operator = expressionNode.getOperator()
            var r = expressionNode.getR()
            if (l is Expression && r is Expression && operator != null) {
                if (l is Operation) {
                    l = visitExpressionNode(l)
                }
                if (r is Operation) {
                    r = visitExpressionNode(r)
                }
                if (l is Variable && r is Variable) {
                    return when (operator) {
                        Operator.SUM -> sumValues(l, r)
                        Operator.SUB -> subtractValues(l, r)
                        Operator.MUL -> multiplyValues(l, r)
                        Operator.DIV -> divideValues(l, r)
                        else -> throw Exception("Invalid Operation")
                    }
                }
            }
        }
        throw Exception("Invalid Expression!")
    }

    override fun visitIfNode(ifNode: IfNode) {
        var conditionValue = ifNode.getCondition()
        if (conditionValue is Variable) {
            if (conditionValue.getType() == PrototypeType.IDENTIFIER) {
                val variableVT: ValueAndTypeV2 = map.getValue(conditionValue.getValue())
                conditionValue = Variable(variableVT.value.toString(), getPrototypeFromType(variableVT.type))
                if (conditionValue.getType() == PrototypeType.BOOLEAN) {
                    if (ifNode.getFalsyNode() == null) {
                        onlyIf(ifNode, conditionValue)
                    } else {
                        ifElse(ifNode, conditionValue)
                    }
                } else {
                    throw Exception("Invalid if statement condition")
                }
            } else if (conditionValue.getType() == PrototypeType.BOOLEAN) {
                if (ifNode.getFalsyNode() == null) {
                    onlyIf(ifNode, conditionValue)
                } else {
                    ifElse(ifNode, conditionValue)
                }
            } else {
                throw Exception("Invalid if statement condition")
            }
        } else {
            throw Exception("If: Condition must be only true / false / variable: Boolean")
        }
    }

    private fun onlyIf(ifNode: IfNode, conditionValue: Variable) {
        val truthyNode = ifNode.getTruthyNode()
        if (conditionValue.getValue() == "true" && truthyNode != null) {
            truthyNode.accept(this)
        }else{
            throw Exception("If: Condition must be only true / false / variable: Boolean")
        }
    }

    private fun ifElse(ifNode: IfNode, conditionValue: Variable) {
        val truthyNode = ifNode.getTruthyNode()
        val falsyNode = ifNode.getFalsyNode()
        if (conditionValue.getValue() == "true" && truthyNode != null) {
            truthyNode.accept(this)
        } else if (conditionValue.getValue() == "false" && falsyNode != null) {
            falsyNode.accept(this)
        } else {
            throw Exception("IfElse: Condition must be only true / false / variable: Boolean")
        }
    }

    private fun setValue(variable: Variable): String {
        var value = variable.getValue()
        if (variable.getType() == PrototypeType.IDENTIFIER) {
            value = map.getValue(value).value.toString()
        }
        return value
    }

    private fun setType(variable: Variable): PrototypeType {
        var type = variable.getType()
        if (variable.getType() == PrototypeType.IDENTIFIER) {
            var variableMap = map.getValue(variable.getValue())
            if (variableMap.type == "string") {
                type = PrototypeType.STRING
            } else if (variableMap.type == "number") {
                type = PrototypeType.NUMBER
            } else if (variableMap.type == "boolean") {
                type = PrototypeType.BOOLEAN
            }
        }
        return type
    }

    private fun sumValues(left: Variable, right: Variable): Variable {
        val lValue = setValue(left)
        val rValue = setValue(right)
        val lType = setType(left)
        val rType = setType(right)
        return when {
            lType == PrototypeType.NUMBER && rType == PrototypeType.NUMBER -> Variable(
                (lValue.toDouble() + rValue.toDouble()).toString(),
                lType,
                left.getLine()
            )

            lType == PrototypeType.STRING && rType == PrototypeType.NUMBER -> Variable(
                lValue + rValue,
                lType,
                left.getLine()
            )

            lType == PrototypeType.NUMBER && rType == PrototypeType.STRING -> Variable(
                lValue + rValue,
                rType,
                right.getLine()
            )

            lType == PrototypeType.STRING && rType == PrototypeType.STRING -> Variable(
                lValue + rValue,
                lType,
                left.getLine()
            )

            else -> throw Exception("Can not sum values $lValue and $rValue")
        }
    }

    private fun subtractValues(left: Variable, right: Variable): Variable {
        val lValue = setValue(left)
        val rValue = setValue(right)
        val lType = setType(left)
        val rType = setType(right)
        return when {
            lType == PrototypeType.NUMBER && rType == PrototypeType.NUMBER -> Variable(
                (lValue.toDouble() - rValue.toDouble()).toString(),
                PrototypeType.NUMBER,
                left.getLine()
            )

            else -> throw Exception("Can not subtract values: $lValue and $rValue")
        }
    }

    private fun multiplyValues(left: Variable, right: Variable): Variable {
        val lValue = setValue(left)
        val rValue = setValue(right)
        val lType = setType(left)
        val rType = setType(right)
        return when {
            lType == PrototypeType.NUMBER && rType == PrototypeType.NUMBER -> Variable(
                (lValue.toDouble() * rValue.toDouble()).toString(),
                PrototypeType.NUMBER,
                left.getLine()
            )

            else -> throw Exception("Can not multiply values: $lValue anda $rValue")
        }
    }

    private fun divideValues(left: Variable, right: Variable): Variable {
        val lValue = setValue(left)
        val rValue = setValue(right)
        val lType = setType(left)
        val rType = setType(right)
        return when {
            lType == PrototypeType.NUMBER && rType == PrototypeType.NUMBER -> Variable(
                (lValue.toDouble() / rValue.toDouble()).toString(),
                PrototypeType.NUMBER,
                right.getLine()
            )

            else -> throw Exception("Can not divide values: $lValue and $rValue")
        }
    }

    override fun visitPrint(printNode: PrintNode) {
        var stringValue: String = visitExpressionNode(printNode.content).toString()
        if (stringValue[0] == '"') {
            stringValue = stringValue.substring(1, stringValue.length - 1)
        }
        if (stringValue[stringValue.length - 1] == '"') {
            stringValue = stringValue.substring(0, stringValue.length - 2)
        }
        printer.print(stringValue.replace(".0", ""))
    }

    override fun visitParentNode(parentNode: ParentNode) {
        for (child in parentNode.getChildren()) {
            when (child) {
                is VariableDeclarationNode -> {
                    visitDeclaration(child)
                }

                is AssignmentNode -> {
                    visitAssignment(child)
                }

                is PrintNode -> {
                    visitPrint(child)
                }

                is IfNode -> {
                    visitIfNode(child)
                }
            }
        }
    }
}
