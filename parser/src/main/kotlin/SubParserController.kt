import exceptions.WrongTokenException
import interfaces.SubParser
import subParsers.AssignmentSubParser
import subParsers.DeclarationSubParser
import subParsers.ExpressionSubParser
import subParsers.PrintSubParser

class SubParserController(val tokens: List<Token>) {
    val assignmentSubParser = AssignmentSubParser(tokens)
    val declarationSubParser = DeclarationSubParser(tokens)
    val printSubParser = PrintSubParser(tokens)

    fun getSubParser(index: Int): SubParser<*> {
        return when (tokens[index].prototypeType) {
            PrototypeType.METHOD_PRINT -> printSubParser
            PrototypeType.IDENTIFIER -> assignmentSubParser
            in declarationTypes -> declarationSubParser
            else -> throw WrongTokenException(tokens[index])
        }
    }
}
