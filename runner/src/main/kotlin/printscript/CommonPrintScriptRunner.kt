package printscript

import CommonParser
import configuration.ConfigClasses
import configurationLinter.ConfigClassesLinter
import errorHandler.ErrorHandler
import fromatter.Formatter
import implementation.Interpreter
import interfaces.ASTNode
import interpreterUtils.Printer
import interpreterUtils.ReadInput
import interpreterUtils.ReadInputImpl
import kotlinx.coroutines.flow.Flow
import lexer.lexer.Lexer
import linter.Linter
import version.Version
import version.getLatestVersion
import java.io.File

class CommonPrintScriptRunner(printer: Printer, private val version: Version = getLatestVersion(), readInput: ReadInput = ReadInputImpl()) : PrintscriptRunner {
    private val lexer = Lexer()
    private val parser = CommonParser()
    private val interpreter = Interpreter.InterpreterConstructor.create(version, printer, readInput)
    private val formatter = Formatter()
    private val linter = Linter()

    override fun runValidation(source: Flow<String>): Boolean {
        return try {
            val tokens = lexer.getTokens(source, version)
            val ast = parser.parseTokens(tokens, version)
            interpreter.interpret(ast)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun runExecution(
        source: Flow<String>,
        errorHandler: ErrorHandler
    ) {
        try {
            val tokens = lexer.getTokens(source, version)
            val ast = parser.parseTokens(tokens, version)
            interpreter.interpret(ast)
        } catch (e: Exception) {
            errorHandler.reportError(e.message)
        }
    }

    override fun runFormatting(source: Flow<String>, configFile: File): String {
        val ast = getNode(source)
        val formatted = formatter.getFormattedCode(ast, version, configFile)
        println(formatted)
        return formatted
    }

    override fun runFormatting(source: Flow<String>, configClasses: ArrayList<ConfigClasses>): String {
        val ast = getNode(source)
        val formatted = formatter.getFormattedCode(ast, version, configClasses)
        println(formatted)
        return formatted
    }

    private fun getNode(source: Flow<String>): ASTNode {
        val tokens = lexer.getTokens(source, version)
        return parser.parseTokens(tokens, version)
    }

    override fun runAnalyzing(source: Flow<String>, configFile: File): String {
        val ast = getNode(source)
        val linted = linter.getLintedCodeCorrection(ast, version, configFile)
        println(linted)
        return linted
    }

    override fun runAnalyzing(source: Flow<String>, configClasses: ArrayList<ConfigClassesLinter>): String {
        val ast = getNode(source)
        val linted = linter.getLintedCodeCorrection(ast, version, configClasses)
        println(linted)
        return linted
    }
}
