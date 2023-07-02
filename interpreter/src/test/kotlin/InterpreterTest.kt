import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import implementation.Interpreter
import org.junit.jupiter.api.Test
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

class InterpreterTest {

    @Test
    fun testSimpleAssignationToken() {
        // Statement: a = 42;
        val node0 = VariableDeclarationNode("a", "number")
        val node1 = AssignmentNode("a", Variable("42", PrototypeType.NUMBER, 0), 1)

        val interpreter = Interpreter.create()
        interpreter.interpret(node0)
        interpreter.interpret(node1)
        var result: Any? = interpreter.getValue("a")
        assert(result == 42)
    }

    @Test
    fun testSimpleSumToken() {
        // Statement: b = 22 + 20;
        val node0 = VariableDeclarationNode("B", "number")
        val node1 = AssignmentNode(
            "B",
            Operation(
                Variable("22.5", PrototypeType.NUMBER, 0),
                Operator.SUM,
                Variable("20", PrototypeType.NUMBER, 0),
                0
            ),
            0
        )

        val interpreter = Interpreter.InterpreterConstructor.create()
        interpreter.interpret(node0)
        interpreter.interpret(node1)
        var result: Any? = interpreter.getValue("B")
        println(result)
        assert(result == 42.5F)
    }

    @Test
    fun testDeclarationForString() {
        // Statement: let b: string = "Hello, world!";
        val node2 = VariableDeclarationNode("b", "string", Variable("Hello, world!", PrototypeType.STRING, 0), 0)

        val interpreter = Interpreter.InterpreterConstructor.create()
        interpreter.interpret(node2)
        var result = interpreter.getValue("b")
        assert(result === "Hello, world!")
    }

    @Test
    fun testDeclarationForIntAndExpression() {
        // Statement: let c: number = 3 + 4 * 5;
        val node3 = VariableDeclarationNode(
            "c",
            "number",
            Operation(
                Variable("3", PrototypeType.NUMBER, 0),
                Operator.SUM,
                Operation(
                    Variable("4", PrototypeType.NUMBER, 0),
                    Operator.MUL,
                    Variable("5", PrototypeType.NUMBER, 0),
                    0
                ),
                0
            ),
            0
        )
        val interpreter = Interpreter.InterpreterConstructor.create()
        interpreter.interpret(node3)
        var result: Any? = interpreter.getValue("c")
        println(result)
        assert(result == 23.0F)
    }

    @Test
    fun testPrint() {
        // Statement: print("Hello, world!");
        val node4 = PrintNode(Variable("Hello, world!", PrototypeType.STRING, 0), 0)

        val interpreter = Interpreter.InterpreterConstructor.create()
        interpreter.interpret(node4)
    }

    @Test
    fun testMultipleLines() {
        // Statement: let a: number = 1 - (2 - 3); // 2
        // Statement: let b: number;
        // Statement: b = a + 1; // 3
        // Statement: print(a + b); // 5
        val node5 = ParentNode(
            listOf(
                VariableDeclarationNode(
                    "a",
                    "number",
                    Operation(
                        Variable("1", PrototypeType.NUMBER, 0),
                        Operator.SUB,
                        Operation(
                            Variable("2", PrototypeType.NUMBER, 0),
                            Operator.SUB,
                            Variable("3", PrototypeType.NUMBER, 0),
                            0
                        ),
                        0
                    ),
                    0
                ),
                VariableDeclarationNode("b", "number", 1),
                AssignmentNode(
                    "b",
                    Operation(
                        Variable("a", PrototypeType.IDENTIFIER, 2),
                        Operator.SUM,
                        Variable("1", PrototypeType.NUMBER, 2),
                        2
                    ),
                    2
                ),
                PrintNode(
                    Operation(
                        Variable("a", PrototypeType.IDENTIFIER, 3),
                        Operator.SUM,
                        Variable("b", PrototypeType.IDENTIFIER, 3),
                        3
                    ),
                    3
                )
            )
        )

        val interpreter = Interpreter.InterpreterConstructor.create()
        interpreter.interpret(node5)
        var resultA: Any? = interpreter.getValue("a")
        var resultB: Any? = interpreter.getValue("b")
        println("a: $resultA")
        println("b: $resultB")
        assert(resultA == 2.0F)
        assert(resultB == 3.0F)
        // y se printea 5
    }

    interface ConsoleInput {
        fun readLine(): String?
    }

    class MyConsoleApp(private val consoleInput: ConsoleInput) {
        fun processInput(): String {
            val input = consoleInput.readLine()
            return "Procesando: $input"
        }
    }

    // no se pueden correr los tests por el readLine()

//    @Test
//    fun testReadInput() {
//        // Statement: a = readInput("Enter a number: ");
//
//        val node0 = VariableDeclarationNode("a", "string")
//        val node8 = AssignmentNode(
//            "a",
//            ReadInputExp(
//                Variable("Enter a number: ", PrototypeType.STRING, 0),
//                0
//            ),
//            0
//        )
//
//    @Test
//    fun testReadInputWithAssignationAndExpression() {
//        // Statement: let a: string = readInput(a+b);
//
//        val node9 = VariableDeclarationNode(
//            "a",
//            "string",
//            ReadInputExp(
//                Operation(
//                    Variable("a", PrototypeType.IDENTIFIER, 0),
//                    Operator.SUM,
//                    Variable("b", PrototypeType.IDENTIFIER, 0),
//                    0
//                ),
//                0
//            ),
//            0
//        )
//
//    }
}
