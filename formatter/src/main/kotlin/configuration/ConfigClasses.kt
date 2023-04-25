package configuration

interface ConfigClasses

sealed interface Space : ConfigClasses

sealed interface LineBrake : ConfigClasses
sealed interface LineIndexed : ConfigClasses

open class LineBrakeForPrintln(private val lines: Int) : LineBrake {
    fun getLines(): Int {
        return this.lines
    }
}

open class SpaceIndexedForIf(private val lines: Int) : LineIndexed {
    fun getLines(): Int {
        return this.lines
    }
}

open class SpaceBeforeColon : Space
open class SpaceAfterColon : Space
open class SpaceBeforeAssignation : Space
open class SpaceAfterAssignation : Space
