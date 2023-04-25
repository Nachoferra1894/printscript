package subParsers

import PrototypeType
import Token
import controllers.ControllerGetter
import types.ParentNode
import version.Version
import java.util.Queue

class CodeParser(private val tokens: Queue<Token>, version: Version) {
    private val controllerGetter = ControllerGetter()
    private val subParserController = controllerGetter.getController(version)

    fun getAstNode(closeType: PrototypeType? = null): ParentNode {
        val parentNode = ParentNode()
        while (!tokens.isEmpty() && tokens.peek().prototypeType != closeType) {
            parentNode.addChild(
                subParserController.getSubParserToken(tokens)
            )
        }
        return parentNode
    }
}
