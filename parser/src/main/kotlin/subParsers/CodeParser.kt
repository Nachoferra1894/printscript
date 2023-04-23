package subParsers

import Token
import TokenMatcher
import controllers.ControllerGetter
import interfaces.ASTNode
import interfaces.SubParser
import types.ParentNode
import version.Version

class CodeParser(private val tokens: List<Token>, private val version: Version) : SubParser<ASTNode>, TokenMatcher(tokens) {
    private val parentNode = ParentNode()
    private val controllerGetter = ControllerGetter()
    private val subParserController = controllerGetter.getController(version)

    override fun getAstNode(nextIndex: Int): Pair<ASTNode, Int> {
        var index = nextIndex
        while (index < tokens.size) {
            val subParser = subParserController.getSubParser(tokens)
            val (astNode, astIndex) = subParser.getAstNode(0)
            parentNode.addChild(astNode)
            index = astIndex
        }
        return Pair(getParentOrFirstChild(parentNode), index)
    }

    private fun getParentOrFirstChild(node: ParentNode): ASTNode {
        return if (node.getChildren().size == 1) {
            node.getChildren()[0]
        } else {
            node
        }
    }
}
