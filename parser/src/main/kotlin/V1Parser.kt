import interfaces.ASTNode
import interfaces.Parser
import subParsers.AssignmentSubParser
import subParsers.DeclarationSubParser
import subParsers.ExpressionSubParser
import subParsers.PrintSubParser
import types.ParentNode

class V1Parser : Parser {
    private lateinit var subParserController: SubParserController

    override fun parseTokens(tokens: List<Token>): ASTNode {
        subParserController = SubParserController(tokens)
        val parentNode = ParentNode()
        var index = 0;
        while (index < tokens.size) {
            val subParser = subParserController.getSubParser(index)
            val (astNode, nextIndex) = subParser.getAstNode(index)
            parentNode.addChild(astNode)
            index = nextIndex

            // TODO add EOL
        }
        return parentNode
    }
}
