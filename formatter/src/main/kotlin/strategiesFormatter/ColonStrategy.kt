package strategiesFormatter

import configuration.ConfigClasses
import configuration.SpaceAfterColon
import configuration.SpaceBeforeColon

class ColonStrategy {
    companion object {
        fun defineColon(config: ArrayList<ConfigClasses>): String {
            var decl = ""
            val spaceBeforeColon = config.filterIsInstance<SpaceBeforeColon>()
            if (spaceBeforeColon.isNotEmpty()) {
                decl += " :"
            } else {
                decl += ":"
            }
            val spaceAfterColon = config.filterIsInstance<SpaceAfterColon>()
            if (spaceAfterColon.isNotEmpty()) {
                decl += " "
            }
            return decl
        }
    }
}
