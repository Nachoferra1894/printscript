package strategiesFormatter

import configuration.ConfigClasses
import configuration.SpaceAfterAssignation
import configuration.SpaceBeforeAssignation

class AssignationStrategy {
    companion object {
        fun defineAssignation(config: ArrayList<ConfigClasses>): String {
            var decl = ""
            val spaceBeforeAssignation = config.filterIsInstance<SpaceBeforeAssignation>()
            if (spaceBeforeAssignation.isNotEmpty()) {
                decl += " ="
            } else {
                decl += "="
            }
            val spaceAfterAssignation = config.filterIsInstance<SpaceAfterAssignation>()
            if (spaceAfterAssignation.isNotEmpty()) {
                decl += " "
            }
            return decl
        }
    }
}
