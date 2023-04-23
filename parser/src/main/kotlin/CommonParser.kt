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
        var braceCount = 0

        tokens.collect { token ->
            lineTokens.add(token)
            if (token.isEOL() && braceCount == 0) {
                println("lineTokens: $lineTokens")
                val codeParser = CodeParser(lineTokens, version)
                val node = codeParser.getAstNode(0).first
                parentNode.addChild(
                    node
                )
                lineTokens.clear()
            } else if (token.prototypeType === PrototypeType.OPEN_BRACE) {
                braceCount++
            } else if (token.prototypeType === PrototypeType.CLOSE_BRACE) {
                braceCount--
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
