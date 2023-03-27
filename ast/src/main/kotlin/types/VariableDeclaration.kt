package types

import expresions.Expression
import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class VariableDeclaration : ASTNode {
//    Declaration can be:
//    let a: number;
//    let b: String = "4";
//    let c: number = 4 / 2;

    private var name: String
    private var type: String // TODO move to an enum??
    private var value: Expression?

    constructor(name: String, type: String) {
        this.name = name
        this.type = type
        this.value = null // Declarations without declaration don't have value
    }

    constructor(name: String, type: String, value: Expression) {
        this.name = name
        this.type = type
        this.value = value
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
