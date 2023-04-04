package generators

import types.AssignmentNode
import types.PrintNode

class LineGenerator {
    companion object {
        fun assigmentGeneratorLine(node: AssignmentNode): String {
            val name = node.name
            val planeValue = node.value.toString()
            return "$name = $planeValue;"
        }

        fun assigmentPrintLine(node: PrintNode): String {
            val planeValue = node.content.toString()
            return "print(\"$planeValue\");"
        }
    }
}
