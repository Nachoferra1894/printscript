package implementation.v1

import PrototypeType
import expresions.Expression
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import interfaces.ASTNode
import interfaces.ASTNodeVisitorV1
import interpreterUtils.Printer
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class InterpreterVisitorV1(
    val map: InterpreterMapV1,
    private val printer: Printer
) : ASTNodeVisitorV1 {

    override fun visitDeclaration(variableDeclaration: VariableDeclarationNode) {
        val name = variableDeclaration.getName()
        val type = variableDeclaration.getType()

        if (!map.exist(name)) {
            map.put(name, ValueAndTypeV1(null, type))
        } else {
            throw Exception("Variable $name already declared! Can't declare the same variable 2 times!")
        }

        if (variableDeclaration.getValue() != null) {
            val assigment = AssignmentNode(name, variableDeclaration.getValue()!!, variableDeclaration.getLine())
            visitAssignment(assigment)
        }
    }

    override fun visitAssignment(assignmentNode: AssignmentNode) {
        val variableName = assignmentNode.name
        if (map.exist(variableName)) {
            val variableType: String = map.getValue(variableName).type
            var literal: Expression = assignmentNode.value
            if (literal is Operation) {
                literal = visitExpressionNode(assignmentNode.value)
            }
            if (literal is Variable) {
                if (variableType == getTypeFromPrototype(literal.getType())) {
                    map.put(variableName, ValueAndTypeV1(literal.getValue(), variableType))
                } else {
                    throw Exception("The variable: $variableName is not of type: $variableType")
                }
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

            else -> {
                throw Exception("Type: $type not exists!")
            }
        }
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
            (lType == PrototypeType.NUMBER) && rType == PrototypeType.NUMBER -> Variable(
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

            else -> throw Exception("Can not sum values: $lValue and $rValue")
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

            else -> throw Exception("Can not multiply values: $lValue and $rValue")
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
        for (child: ASTNode in parentNode.getChildren()) {
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
            }
        }
    }
}
