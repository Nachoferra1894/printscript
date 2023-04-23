package configuration

interface ConfigClasses

sealed interface Space : ConfigClasses

sealed interface LineBrake : ConfigClasses

open class LineBrakeForPrintln(private val lines: Int) : LineBrake {
    fun getLines(): Int {
        return this.lines
    }
}

open class SpaceBeforeColon : Space
open class SpaceAfterColon : Space
open class SpaceBeforeAssignation : Space
open class SpaceAfterAssignation : Space
