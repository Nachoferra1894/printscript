package subParsers

import SubParserController
import Token
import TokenMatcher
import interfaces.SubParser
import types.ParentNode
import version.Version

class CodeParser(private val tokens: List<Token>, version: Version) : SubParser<ParentNode>, TokenMatcher(tokens) {
    private val parentNode = ParentNode()
    private val subParserController = SubParserController(version)

    override fun getAstNode(nextIndex: Int): Pair<ParentNode, Int> {
        var index = nextIndex
        while(!tokens[index].isEOL()) {
            val subParser = subParserController.getSubParser(tokens)
            val astNode = subParser.getAstNode(0)
            parentNode.addChild(astNode.first)
            lineTokens.clear()
        }
        return Pair(parentNode, index)
    }
}
