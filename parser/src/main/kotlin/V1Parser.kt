import interfaces.ASTNode
import interfaces.Parser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import subParsers.CodeParser
import types.ParentNode
import version.Version

class V1Parser : Parser {

    override fun parseTokens(tokens: Flow<Token>, version: Version): ASTNode = runBlocking {
        val lineTokens = mutableListOf<Token>()
        val parentNode = ParentNode()

        tokens.collect { token ->
            lineTokens.add(token)
            if (token.isEOL()) {
                println("lineTokens: $lineTokens")
                val codeParser = CodeParser(lineTokens, version)
                val node = codeParser.getAstNode(0).first
                if (node.getChildren().size == 1) {
                    parentNode.addChild(node.getChildren()[0])
                } else {
                    parentNode.addChild(node)
                }
                lineTokens.clear()
            }
        }
        parentNode
    }
}
