package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitorCommon

class VariableDeclarationNode : ASTNode {
    private var name: String
    private var type: String // TODO move to an enum??
    private var value: Expression?
    private var isMutable: Boolean
    private var line: Int

    // add isMutable as a third parameter, default to true
    constructor(name: String, type: String, line: Int = 0, isMutable: Boolean = true) {
        this.name = name
        this.name = name
        this.type = type
        this.value = null // Declarations without declaration don't have value
        this.isMutable = isMutable
        this.line = line
    }

    constructor(name: String, type: String, value: Expression, line: Int = 0, isMutable: Boolean = true) {
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

    fun isMutable(): Boolean {
        return this.isMutable
    }

    fun getValue(): Expression? {
        return this.value
    }

    override fun accept(visitor: ASTNodeVisitorCommon) {
        visitor.visitDeclaration(this)
    }

    override fun toString(): String {
        val mutable = if (isMutable) "let" else "const"
        return if (value !== null) {
            "$mutable $name: $type = $value"
        } else {
            "$mutable $name: $type"
        }
    }
}
