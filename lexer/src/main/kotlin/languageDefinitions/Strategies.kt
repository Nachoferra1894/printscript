package languageDefinitions

import Token
import lexer.exceptions.NoTokenException
import strategies.AssignationStrategy
import strategies.BooleanStrategy
import strategies.ColonStrategy
import strategies.ConstStrategy
import strategies.DivisionStrategy
import strategies.ElseStrategy
import strategies.FinalStrategy
import strategies.IdentifierStrategy
import strategies.IfStrategy
import strategies.KeyStrategies
import strategies.LetStrategy
import strategies.MultiplicationStrategy
import strategies.NumberStrategy
import strategies.ParenthesisStrategy
import strategies.PlusStrategy
import strategies.PrintStrategy
import strategies.ReadInputStrategy
import strategies.SpaceStrategy
import strategies.Strategy
import strategies.StringStrategy
import strategies.SubstractionStrategy
import strategies.ValueStrategy
import version.V1
import version.V2
import version.Version

class Strategies {

    private val listV1: ArrayList<Strategy> = ArrayList()
    private val listV2: ArrayList<Strategy> = ArrayList()

    init {
        addStrategiesV1()
        addStrategiesV2()
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

    private fun addStrategiesV2() {
        listV2.add(SpaceStrategy())
        listV2.add(LetStrategy())
        listV2.add(StringStrategy())
        listV2.add(NumberStrategy())
        listV2.add(BooleanStrategy())
        listV2.add(IfStrategy())
        listV2.add(ElseStrategy())
        listV2.add(ConstStrategy())
        listV2.add(ColonStrategy())
        listV2.add(DivisionStrategy())
        listV2.add(AssignationStrategy())
        listV2.add(MultiplicationStrategy())
        listV2.add(PlusStrategy())
        listV2.add(SubstractionStrategy())
        listV2.add(FinalStrategy())
        listV2.add(PrintStrategy())
        listV2.add(ReadInputStrategy())
        listV2.add(ValueStrategy())
        listV2.add(IdentifierStrategy())
        listV2.add(ParenthesisStrategy())
        listV2.add(KeyStrategies())
    }

    fun defineTokens(line: String, index: Int, lineIndex: Int, version: Version): Token {
        return when (version) {
            is V1 -> strategyV1(line, index, lineIndex)
            is V2 -> strategyV2(line, index, lineIndex)
        }
    }

    private fun strategyV1(line: String, index: Int, lineIndex: Int): Token {
        val strategy: Strategy = listV1.find { it.isStrategy(line, index) }
            ?: throw NoTokenException("No token with this expression " + line[index])
        return strategy.getToken(line, index, lineIndex)
    }
    private fun strategyV2(line: String, index: Int, lineIndex: Int): Token {
        val strategy: Strategy = listV2.find { it.isStrategy(line, index) }
            ?: throw NoTokenException("No token with this expression " + line[index])
        return strategy.getToken(line, index, lineIndex)
    }
}
