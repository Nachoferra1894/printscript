import interfaces.ASTNode
import interfaces.Parser
import types.ParentNode

class V1Parser : Parser {
    private lateinit var subParserController: SubParserController

    override fun parseTokens(tokens: List<Token>): ASTNode {
        subParserController = SubParserController(tokens)
        val parentNode = ParentNode()
        var index = 0
        while (index < tokens.size) {
            val subParser = subParserController.getSubParser(index)
            val (astNode, nextIndex) = subParser.getAstNode(index)
            parentNode.addChild(astNode)
            index = nextIndex
        }
        if (parentNode.getChildren().size == 1) {
            return parentNode.getChildren()[0]
        }
        return parentNode
    }
}
