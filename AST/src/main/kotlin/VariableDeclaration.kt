import interfaces.ASTNode
import interfaces.ASTNodeVisitor

class VariableDeclaration: ASTNode {
//    Declaration can be:
//    let a: number;
//    let b: String = "4";
//    let c: number = 4 / 2;

    var name: String
    var type: String //TODO move to an enum??


    override fun accept(visitor: ASTNodeVisitor) {
        visitor.visitDeclaration(this)
    }
}