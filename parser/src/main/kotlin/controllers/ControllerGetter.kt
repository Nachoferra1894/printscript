package controllers

import version.V1
import version.V2
import version.Version

class ControllerGetter {
    fun getController(version: Version): SubParserController {
        return when (version) {
            is V1 -> V1SubParserController()
            is V2 -> V2SubParserController()
            else -> throw Exception("Version not found")
        }
    }
}
