package strategies

import lexer.strategies.TokenStrategy.Companion.finalStrategy
import lexer.strategies.TokenStrategy.Companion.identifierStrategy
import lexer.strategies.TokenStrategy.Companion.letStrategy
import lexer.strategies.TokenStrategy.Companion.operationStrategy
import lexer.strategies.TokenStrategy.Companion.typeStrategy
import lexer.strategies.TokenStrategy.Companion.valueStrategy
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StrategyTest {
    private val numberLine = "let a: number = 12;"
    private val plusOperationStrategy = "a + a"
    private val subtractOperationStrategy = "a - a"
    private val multiplicationOperationStrategy = "a * a"
    private val divisionOperationStrategy = "a / a"
    private val stringLine = "let a: string = \"HI!\";"

    @Test
    fun testLetStrategy() {
        val indexCorrect = 0
        val indexIncorrect = 1
        assertTrue(letStrategy(numberLine, indexCorrect))
        assertFalse(letStrategy(numberLine, indexIncorrect))
    }

    @Test
    fun testIdentifierStrategy() {
        val indexCorrect = 4
        assertTrue(identifierStrategy(numberLine, indexCorrect))
    }

    @Test
    fun testValueStrategy() {
        val indexIncorrect = 0
        val indexCorrect = 16
        assertTrue(valueStrategy(numberLine, indexCorrect))
        assertFalse(valueStrategy(numberLine, indexIncorrect))
        assertTrue(valueStrategy(stringLine, indexCorrect))
        assertFalse(valueStrategy(stringLine, indexIncorrect))
    }

    @Test
    fun testFinalStrategy() {
        val indexIncorrect = 0
        val indexCorrect = 18
        assertTrue(finalStrategy(numberLine, indexCorrect))
        assertFalse(finalStrategy(numberLine, indexIncorrect))
    }

    @Test
    fun testOperationStrategyAssignation() {
        val indexIncorrect = 0
        val indexCorrect = 14
        assertTrue(operationStrategy(numberLine, indexCorrect))
        assertFalse(operationStrategy(numberLine, indexIncorrect))
    }

    @Test
    fun testOperationStrategyPlus() {
        val indexIncorrect = 0
        val indexCorrect = 2
        assertTrue(operationStrategy(plusOperationStrategy, indexCorrect))
        assertFalse(operationStrategy(plusOperationStrategy, indexIncorrect))
    }

    @Test
    fun testOperationStrategySubtract() {
        val indexIncorrect = 0
        val indexCorrect = 2
        assertTrue(operationStrategy(subtractOperationStrategy, indexCorrect))
        assertFalse(operationStrategy(subtractOperationStrategy, indexIncorrect))
    }

    @Test
    fun testOperationStrategyMultiplication() {
        val indexIncorrect = 0
        val indexCorrect = 2
        assertTrue(operationStrategy(multiplicationOperationStrategy, indexCorrect))
        assertFalse(operationStrategy(multiplicationOperationStrategy, indexIncorrect))
    }

    @Test
    fun testOperationStrategyDivision() {
        val indexIncorrect = 0
        val indexCorrect = 2
        assertTrue(operationStrategy(divisionOperationStrategy, indexCorrect))
        assertFalse(operationStrategy(divisionOperationStrategy, indexIncorrect))
    }

    @Test
    fun testOperationStrategyNumberType() {
        val indexIncorrect = 0
        val indexCorrect = 7
        assertTrue(typeStrategy(numberLine, indexCorrect))
        assertFalse(typeStrategy(numberLine, indexIncorrect))
    }

    @Test
    fun testOperationStrategyStringType() {
        val indexIncorrect = 0
        val indexCorrect = 7
        assertTrue(typeStrategy(stringLine, indexCorrect))
        assertFalse(typeStrategy(stringLine, indexIncorrect))
    }
}
