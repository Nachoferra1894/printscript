package configuration

interface ConfigClasses

sealed interface PrintCase : ConfigClasses

sealed interface TextCase : ConfigClasses

open class CamelCase : TextCase {
    companion object
}
open class SnakeCase : TextCase

open class PrintNormal : PrintCase
open class PrintOperations : PrintCase
