import interfaces.ASTNode
import interfaces.Parser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import subParsers.CodeParser
import types.ParentNode
import version.Version
import java.util.LinkedList
import java.util.Queue

class CommonParser : Parser {

    override fun parseTokens(tokens: Flow<Token>, version: Version): ASTNode = runBlocking {
        val tokenQueue: Queue<Token> = LinkedList(tokens.toList())
        val codeParser = CodeParser(tokenQueue, version)
        val parentNode = codeParser.getAstNode()
        getParentOrFirstChild(parentNode)
    }

    private fun getParentOrFirstChild(node: ParentNode): ASTNode {
        return if (node.getChildren().size == 1) {
            node.getChildren()[0]
        } else {
            node
        }
    }
}
