package strategiesFormatter

import configuration.ConfigClasses
import configuration.LineBrakeForPrintln
import types.PrintNode

class PrintlnStrategy {
    companion object {
        fun defineValue(config: ArrayList<ConfigClasses>, printNode: PrintNode): String {
            return defineLines(config) + printNode.toString()
        }

        private fun defineLines(config: ArrayList<ConfigClasses>): String {
            val linesBrake = config.filterIsInstance<LineBrakeForPrintln>()
            if (linesBrake.isNotEmpty()) {
                val lineBrake = linesBrake[0]
                when (lineBrake.getLines()) {
                    0 -> return ""
                    1 -> return "\n"
                    2 -> return "\n\n"
                    else -> return "\n\n"
                }
            }
            return ""
        }
    }
}
