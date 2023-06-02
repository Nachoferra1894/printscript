import exceptions.WrongTokenException
import interfaces.ASTNode
import interfaces.Parser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import subParsers.CodeParser
import types.IfNode
import types.ParentNode
import version.Version
import java.util.LinkedList
import java.util.Queue

class CommonParser : Parser {

    override fun parseTokens(tokens: Flow<Token>, version: Version): ASTNode = runBlocking {
        val tokenQueue: Queue<Token> = LinkedList()
        val parentNode = ParentNode()
        var isInIf = false
        var isInElse = false
        var isInIfCondition = false

        tokens.collect { token ->
            if (token.isEOL() && !isInIf && !isInIfCondition) {
                tokenQueue.add(token)
                val codeParser = CodeParser(tokenQueue, version)
                parentNode.addChild(
                    getParentOrFirstChild(codeParser.getAstNode())
                )
            } else if (token.isIf()) {
                tokenQueue.add(token)
                isInIfCondition = true
            } else if (token.isCloseIf() && isInIfCondition) {
                tokenQueue.add(token)
                val codeParser = CodeParser(tokenQueue, version)
                val node = codeParser.getAstNode(PrototypeType.CLOSE_PARENTHESIS).getFirstChild()
                parentNode.addChild(node)
                isInIfCondition = false
            } else if (token.isOpenBlock()) {
                isInIf = true
            } else if (token.isCloseBlock() && isInIf) {
                val ifNode = parentNode.getFirstChild()
                if (ifNode is IfNode) {
                    val codeParser = CodeParser(tokenQueue, version)
                    if (!isInElse) {
                        ifNode.addTruthyNode(codeParser.getAstNode(PrototypeType.CLOSE_BRACE))
                    } else {
                        ifNode.addFalsyNode(codeParser.getAstNode(PrototypeType.CLOSE_BRACE))
                    }
                } else {
                    throw WrongTokenException(token)
                }
                isInIf = false
                isInElse = false
            } else if (token.isElseBlock()) {
                isInElse = true
            } else {
                tokenQueue.add(token)
            }
        }
        getParentOrFirstChild(parentNode)
    }

    private fun getParentOrFirstChild(node: ParentNode): ASTNode {
        return if (node.getChildren().size == 1) {
            node.getFirstChild()
        } else {
            node
        }
    }
}
