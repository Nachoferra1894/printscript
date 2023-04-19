package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class VariableDeclarationNode : ASTNode {
    private var name: String
    private var type: String // TODO move to an enum??
    private var value: Expression?
    private var isMutable: Boolean
    private var line: Int

    // add isMutable as a third parameter, default to true
    constructor(name: String, type: String, isMutable: Boolean = true, line: Int) {
        this.name = name
        this.name = name
        this.type = type
        this.value = null // Declarations without declaration don't have value
        this.isMutable = isMutable
        this.line = line
    }

    constructor(name: String, type: String, value: Expression,  isMutable: Boolean = true, line: Int) {
        this.name = name
        this.type = type
        this.value = value
        this.line = line
        this.isMutable = isMutable
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
            "$mutable $name: $type = $value"
        } else {
            "$mutable $name: $type"
        }
    }
}
