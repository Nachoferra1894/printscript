package implementation

import PrototypeType
import expresions.Expression
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import interfaces.ASTNodeVisitor
import types.AssignmentNode
import types.IfNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode
import version.Version
import kotlin.Error

class InterpreterVisitor(
    val map: InterpreterMap,
    private val printer: PrinterImpl,
    private val version: Version
) : ASTNodeVisitor {

    override fun visitDeclaration(variableDeclaration: VariableDeclarationNode) {
        val name = variableDeclaration.getName()
        val type = variableDeclaration.getType()

        map.put(name, ValueAndType(null, type))
        if (variableDeclaration.getValue() != null) {
            val assigment = AssignmentNode(name, variableDeclaration.getValue()!!, variableDeclaration.getLine())
            visitAssignment(assigment)
        }
    }

    override fun visitAssignment(assignmentNode: AssignmentNode) {
        val variableName = assignmentNode.name
        if (map.exist(variableName)) {
            val variableType: String = map.getValue(variableName).type
            val literal = assignmentNode.value.accept(this)
            if (literal is Variable && variableType == getTypeFromPrototype(literal.getType())) {
                map.put(variableName, ValueAndType(literal.getValue(), variableType))
            }
        } else {
            throw Error("The variable: $variableName does not exist!")
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
                throw Error("Type not exists!")
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
                        else -> throw Error("Invalid Operation")
                    }
                }
            }
        }
        throw Error("Invalid Expression!")
    }

    override fun visitIfNode(ifNode: IfNode) {
        TODO("Not yet implemented")
    }

    private fun sumValues(left: Variable, right: Variable): Variable {
        var lValue = left.getValue()
        var rValue = right.getValue()
        val lType = left.getType()
        val rType = right.getType()
        if (lType == PrototypeType.IDENTIFIER) {
            lValue = map.getValue(lValue).toString()
        }
        if (rType == PrototypeType.IDENTIFIER) {
            rValue = map.getValue(rValue).toString()
        }
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

            else -> throw Error("Can not sum values")
        }
    }

    private fun subtractValues(left: Variable, right: Variable): Variable {
        var lValue = left.getValue()
        var rValue = right.getValue()
        if (left.getType() == PrototypeType.IDENTIFIER) {
            lValue = map.getValue(lValue).toString()
        }
        if (right.getType() == PrototypeType.IDENTIFIER) {
            rValue = map.getValue(rValue).toString()
        }
        return when {
            left.getType() == PrototypeType.NUMBER && right.getType() == PrototypeType.NUMBER -> Variable(
                (lValue.toDouble() - rValue.toDouble()).toString(),
                PrototypeType.NUMBER,
                left.getLine()
            )

            else -> throw Error("Can not subtract values")
        }
    }

    private fun multiplyValues(left: Variable, right: Variable): Variable {
        var lValue = left.getValue()
        var rValue = right.getValue()
        if (left.getType() == PrototypeType.IDENTIFIER) {
            lValue = map.getValue(lValue).toString()
        }
        if (right.getType() == PrototypeType.IDENTIFIER) {
            rValue = map.getValue(rValue).toString()
        }
        return when {
            left.getType() == PrototypeType.NUMBER && right.getType() == PrototypeType.NUMBER -> Variable(
                (lValue.toDouble() * rValue.toDouble()).toString(),
                PrototypeType.NUMBER,
                left.getLine()
            )

            else -> throw Error("Can not multiply values")
        }
    }

    private fun divideValues(left: Variable, right: Variable): Variable {
        var lValue = left.getValue()
        var rValue = right.getValue()
        if (left.getType() == PrototypeType.IDENTIFIER) {
            lValue = map.getValue(lValue).toString()
        }
        if (right.getType() == PrototypeType.IDENTIFIER) {
            rValue = map.getValue(rValue).toString()
        }
        return when {
            left.getType() == PrototypeType.NUMBER && right.getType() == PrototypeType.NUMBER -> Variable(
                (lValue.toDouble() / rValue.toDouble()).toString(),
                PrototypeType.NUMBER,
                right.getLine()

            )

            else -> throw Error("Can not divide values")
        }
    }

    override fun visitPrint(printNode: PrintNode) {
        val stringValue: String = visitExpressionNode(printNode.content).toString()
        printer.print(stringValue.replace(".0", ""))
    }

    override fun visitParentNode(parentNode: ParentNode) {
        for (child in parentNode.getChildren()) {
            child.accept(this)
        }
    }
}
