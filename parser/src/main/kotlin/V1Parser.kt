import interfaces.ASTNode
import interfaces.Parser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import types.ParentNode

class V1Parser : Parser {

    override fun parseTokens(tokens: Flow<Token>): ASTNode = runBlocking {
        val parentNode = ParentNode()
        val subParserController = SubParserController()
        val lineTokens = mutableListOf<Token>()

        tokens.collect { token ->
            lineTokens.add(token)
            if (token.isEOL()) {
                println("lineTokens: $lineTokens")
                val subParser = subParserController.getSubParser(lineTokens)
                val astNode = subParser.getAstNode(0)
                parentNode.addChild(astNode.first)
                lineTokens.clear()
            }
        }
        if (parentNode.getChildren().size == 1) {
            parentNode.getChildren()[0]
        } else {
            parentNode
        }
    }
}
