import exceptions.WrongTokenException
import interfaces.SubParser
import subParsers.AssignmentSubParser
import subParsers.DeclarationSubParser
import subParsers.PrintSubParser
import version.Version

class SubParserController(val version: Version) {
    fun getSubParser(tokens: List<Token>): SubParser<*> {
        return when (tokens[0].prototypeType) {
            PrototypeType.METHOD_PRINT -> PrintSubParser(tokens, version)
            PrototypeType.IDENTIFIER -> AssignmentSubParser(tokens, version)
            in declarationTypes(version) -> DeclarationSubParser(tokens, version)
            else -> throw WrongTokenException(tokens[0]) // Having a readInput here does not make sense
        }
    }
}
