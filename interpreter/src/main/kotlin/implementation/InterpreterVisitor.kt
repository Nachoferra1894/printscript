package implementation

import PrototypeType
import expresions.Expression
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import interfaces.ASTNodeVisitor
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode
import kotlin.Error

class InterpreterVisitor(
    val map: InterpreterMap,
    private val printer: PrinterImpl
) : ASTNodeVisitor {

    override fun visitDeclaration(variableDeclaration: VariableDeclarationNode) {
        val name = variableDeclaration.getName()
        val type = variableDeclaration.getType()

        map.put(name, ValueAndType(null, type))
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

    override fun visitExpressionNode(expressionNode: Expression): Variable { // TODO this should not return anything
        if (expressionNode is Variable) {
            if (expressionNode.getType() == PrototypeType.IDENTIFIER) {
                val variableVT = map.getValue(expressionNode.getValue())
                return Variable(variableVT.value.toString(), getPrototypeFromType(variableVT.type))
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

    // TODO all of these should also accept identifiers

    private fun sumValues(left: Variable, right: Variable): Variable {
        val lType = left.getType()
        val rType = right.getType()
        return when {
            lType == PrototypeType.NUMBER && rType == PrototypeType.NUMBER -> Variable((left.getValue().toDouble() + right.getValue().toDouble()).toString(), lType)
            lType == PrototypeType.STRING && rType == PrototypeType.NUMBER -> Variable(left.getValue() + right.getValue(), lType)
            lType == PrototypeType.NUMBER && rType == PrototypeType.STRING -> Variable(left.getValue() + right.getValue(), rType)
            lType == PrototypeType.STRING && rType == PrototypeType.STRING -> Variable(left.getValue() + right.getValue(), lType)
            else -> throw Error("Can not sum values")
        }
    }

    private fun subtractValues(left: Variable, right: Variable): Variable {
        return when {
            left.getType() == PrototypeType.NUMBER && right.getType() == PrototypeType.NUMBER -> Variable((left.getValue().toDouble() - right.getValue().toDouble()).toString(), PrototypeType.NUMBER)
            else -> throw Error("Can not subtract values")
        }
    }

    private fun multiplyValues(left: Variable, right: Variable): Variable {
        return when {
            left.getType() == PrototypeType.NUMBER && right.getType() == PrototypeType.NUMBER -> Variable((left.getValue().toDouble() * right.getValue().toDouble()).toString(), PrototypeType.NUMBER)
            else -> throw Error("Can not multiply values")
        }
    }

    private fun divideValues(left: Variable, right: Variable): Variable {
        return when {
            left.getType() == PrototypeType.NUMBER && right.getType() == PrototypeType.NUMBER -> Variable((left.getValue().toDouble() / right.getValue().toDouble()).toString(), PrototypeType.NUMBER)
            else -> throw Error("Can not divide values")
        }
    }

    override fun visitPrint(printNode: PrintNode) {
        printNode.content.accept(this)
    }

    override fun visitParentNode(parentNode: ParentNode) {
        for (child in parentNode.getChildren()) {
            child.accept(this)
        }
    }
}
