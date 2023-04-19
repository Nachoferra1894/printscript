package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class VariableDeclarationNode : ASTNode {
    private var name: String
    private var type: String // TODO move to an enum??
    private var value: Expression?
    private var line: Int

    constructor(name: String, type: String, line: Int) {
        this.name = name
        this.type = type
        this.value = null // Declarations without declaration don't have value
        this.line = line
    }

    constructor(name: String, type: String, value: Expression, line: Int) {
        this.name = name
        this.type = type
        this.value = value
        this.line = line
    }

    override fun getLine(): Int {
        return line
    }

    fun getName(): String {
        return this.name
    }
    fun getType(): String {
        return this.type
    }

    fun getValue(): Expression? {
        return this.value
    }

    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitDeclaration(this)
    }

    override fun toString(): String {
        return if (value !== null) {
            "let $name: $type = $value"
        } else {
            "let $name: $type"
        }
    }
}
