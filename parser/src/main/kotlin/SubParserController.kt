import exceptions.WrongTokenException
import interfaces.SubParser
import subParsers.AssignmentSubParser
import subParsers.DeclarationSubParser
import subParsers.ExpressionSubParser
import subParsers.IfSubParser
import subParsers.PrintSubParser
import subParsers.ReadInputSubParser
import version.V2
import version.Version

class SubParserController(val version: Version) {
    fun getSubParser(tokens: List<Token>): SubParser<*> {
        return when (tokens[0].prototypeType) {
            PrototypeType.METHOD_PRINT -> PrintSubParser(tokens, version)
            PrototypeType.IDENTIFIER -> AssignmentSubParser(tokens, version)
            in declarationTypes(version) -> DeclarationSubParser(tokens, version)
            PrototypeType.IF -> IfSubParser(tokens, version)
            else -> throw WrongTokenException(tokens[0]) // Having a readInput here does not make sense
        }
    }

    fun getExpressionParser(tokens: List<Token>, index: Int): SubParser<*> {
        return if (tokens[index].prototypeType == PrototypeType.SPACE) {
            getExpressionParser(tokens, index + 1)
        } else if (tokens[index].prototypeType in variableTypes(version)) {
            ExpressionSubParser(tokens, version)
        } else if (tokens[index].prototypeType == PrototypeType.METHOD_READ_INPUT && version is V2) {
            ReadInputSubParser(tokens, version)
        } else {
            throw WrongTokenException(tokens[index]) // Having a readInput here does not make sense
        }
    }
}
