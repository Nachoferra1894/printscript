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

    private val listV1: MutableSet<Strategy> = mutableSetOf()
    private val listV2: MutableSet<Strategy> = mutableSetOf()

    init {
        listV1.addAll(
            addStrategiesV1()
        )
        listV2.addAll(
            addStrategiesV2()
        )
        listV2.addAll(
            addStrategiesV1()
        )
    }

    private fun addStrategiesV1(): List<Strategy> {
        return listOf(
            SpaceStrategy(),
            StringStrategy(),
            LetStrategy(),
            StringStrategy(),
            NumberStrategy(),
            ColonStrategy(),
            DivisionStrategy(),
            AssignationStrategy(),
            MultiplicationStrategy(),
            PlusStrategy(),
            SubstractionStrategy(),
            FinalStrategy(),
            PrintStrategy(),
            ValueStrategy(),
            IdentifierStrategy(),
            ParenthesisStrategy()
        )
    }

    private fun addStrategiesV2(): List<Strategy> {
        return listOf(
            ReadInputStrategy(),
            KeyStrategies(),
            BooleanStrategy(),
            ElseStrategy(),
            ConstStrategy(),
            IfStrategy()
        )
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
