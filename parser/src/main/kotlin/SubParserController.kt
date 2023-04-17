import exceptions.WrongTokenException
import interfaces.SubParser
import subParsers.AssignmentSubParser
import subParsers.DeclarationSubParser
import subParsers.PrintSubParser

class SubParserController() {
    fun getSubParser(tokens: List<Token>): SubParser<*> {
        return when (tokens[0].prototypeType) {
            PrototypeType.METHOD_PRINT -> PrintSubParser(tokens)
            PrototypeType.IDENTIFIER -> AssignmentSubParser(tokens)
            in declarationTypes -> DeclarationSubParser(tokens)
            else -> throw WrongTokenException(tokens[0])
        }
    }
}
