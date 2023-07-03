package configurationLinter

interface ConfigClassesLinter

sealed interface PrintCase : ConfigClassesLinter
sealed interface ReadInputCase : ConfigClassesLinter
sealed interface TextCase : ConfigClassesLinter

open class CamelCase : TextCase
open class SnakeCase : TextCase
open class PrintNormal : PrintCase
open class PrintOperations : PrintCase

open class ReadInputOperations : ReadInputCase
open class ReadInputNormal : ReadInputCase
