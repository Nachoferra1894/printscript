package interfaces

import types.IfNode

interface ASTNodeVisitorV1 : ASTNodeVisitorCommon
interface ASTNodeVisitorV2 : ASTNodeVisitorCommon {
    fun visitIfNode(ifNode: IfNode)
}
