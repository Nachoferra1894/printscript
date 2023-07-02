package configurationLinter

interface ConfigClasses

sealed interface PrintCase : ConfigClasses
sealed interface ReadInputCase : ConfigClasses
sealed interface TextCase : ConfigClasses

open class CamelCase : TextCase
open class SnakeCase : TextCase
open class PrintNormal : PrintCase
open class PrintOperations : PrintCase

open class ReadInputOperations : ReadInputCase
open class ReadInputNormal : ReadInputCase
