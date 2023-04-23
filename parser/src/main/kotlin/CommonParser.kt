
import interfaces.ASTNode
import interfaces.Parser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import subParsers.CodeParser
import types.ParentNode
import version.Version

class CommonParser : Parser {

    override fun parseTokens(tokens: Flow<Token>, version: Version): ASTNode = runBlocking {
        val lineTokens = mutableListOf<Token>()
        val parentNode = ParentNode()

        tokens.collect { token ->
            lineTokens.add(token)
            if (token.isEOL()) {
                println("lineTokens: $lineTokens")
                val codeParser = CodeParser(lineTokens, version)
                val node = codeParser.getAstNode(0).first
                parentNode.addChild(
                    node
                )
                lineTokens.clear()
            } else if (token.prototypeType === PrototypeType.OPEN_PARENTHESIS) {
            }
        }
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
