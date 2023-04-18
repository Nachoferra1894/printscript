package languageDefinitions

import Token
import lexer.exceptions.NoTokenException
import strategies.AssignationStrategy
import strategies.ColonStrategy
import strategies.DivisionStrategy
import strategies.FinalStrategy
import strategies.IdentifierStrategy
import strategies.LetStrategy
import strategies.MultiplicationStrategy
import strategies.NumberStrategy
import strategies.ParenthesisStrategy
import strategies.PlusStrategy
import strategies.PrintStrategy
import strategies.SpaceStrategy
import strategies.Strategy
import strategies.StringStrategy
import strategies.SubstractionStrategy
import strategies.ValueStrategy

class Strategies {

    private val listV1: ArrayList<Strategy> = ArrayList()
    init {
        addStrategiesV1()
    }
    private fun addStrategiesV1() {
        listV1.add(SpaceStrategy())
        listV1.add(LetStrategy())
        listV1.add(StringStrategy())
        listV1.add(NumberStrategy())
        listV1.add(ColonStrategy())
        listV1.add(DivisionStrategy())
        listV1.add(AssignationStrategy())
        listV1.add(MultiplicationStrategy())
        listV1.add(PlusStrategy())
        listV1.add(SubstractionStrategy())
        listV1.add(FinalStrategy())
        listV1.add(PrintStrategy())
        listV1.add(ValueStrategy())
        listV1.add(IdentifierStrategy())
        listV1.add(ParenthesisStrategy())
    }

    fun defineTokens(line: String, index: Int): Token {
        val strategy: Strategy = listV1.find { it.isStrategy(line, index) }
            ?: throw NoTokenException("No token with this expression " + line[index])

        return strategy.getToken(line, index,0) // MICA FIXEA ESTE 0
    }
}
