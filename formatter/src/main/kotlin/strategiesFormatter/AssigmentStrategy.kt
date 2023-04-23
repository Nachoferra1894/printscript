package strategiesFormatter

import configuration.ConfigClasses
import strategiesFormatter.AssignationStrategy.Companion.defineAssignation
import types.AssignmentNode

class AssigmentStrategy {
    companion object {
        fun defineValue(config: ArrayList<ConfigClasses>, assigmentNode: AssignmentNode): String {
            return assigmentNode.name + defineAssignation(config) + assigmentNode.value.toString()
        }
    }
}
