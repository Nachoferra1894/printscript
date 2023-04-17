package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class VariableDeclarationNode : ASTNode {
    private var name: String
    private var type: String // TODO move to an enum??
    private var value: Expression?
    private var isMutable: Boolean

    // add isMutable as a third parameter, default to true
    constructor(name: String, type: String, isMutable: Boolean = true) {
        this.name = name
        this.type = type
        this.value = null // Declarations without declaration don't have value
        this.isMutable = isMutable
    }

    constructor(name: String, type: String, value: Expression, isMutable: Boolean = true) {
        this.name = name
        this.type = type
        this.value = value
        this.isMutable = isMutable
    }

    override fun accept(visitor: ASTNodeVisitor) {
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
