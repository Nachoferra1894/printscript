import expresions.Operator
import expresions.types.Operation
import expresions.types.ReadInputExp
import expresions.types.Variable
import implementation.Interpreter
import org.junit.jupiter.api.Test
import types.*
import version.V1
import version.V2

class InterpreterTest {

    @Test
    fun testSimpleAssignationToken() {
        // Statement: a = 42;
        val node0 = VariableDeclarationNode("a", "number")
        val node1 = AssignmentNode("a", Variable("42", PrototypeType.NUMBER, 0), 1)

        val interpreter = Interpreter.create(V1())
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
            Operation(Variable("22.5", PrototypeType.NUMBER, 0), Operator.SUM, Variable("20", PrototypeType.NUMBER, 0), 0),
            0
        )

        val interpreter = Interpreter.InterpreterConstructor.create(V1())
        interpreter.interpret(node0)
        interpreter.interpret(node1)
        var result : Any? = interpreter.getValue("B")
        println(result)
        assert(result == 42.5F)
    }

    @Test
    fun testDeclarationForString() {
        // Statement: let b: string = "Hello, world!";
        val node2 = VariableDeclarationNode("b", "string", Variable("Hello, world!", PrototypeType.STRING, 0), 0)

        val interpreter = Interpreter.InterpreterConstructor.create(V1())
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
                Operation(Variable("4", PrototypeType.NUMBER, 0), Operator.MUL, Variable("5", PrototypeType.NUMBER, 0), 0),
                0
            ),
            0
        )
        val interpreter = Interpreter.InterpreterConstructor.create(V1())
        interpreter.interpret(node3)
        var result : Any? = interpreter.getValue("c")
        println(result)
        assert(result == 23.0F)
    }

    @Test
    fun testPrint() {
        // Statement: print("Hello, world!");
        val node4 = PrintNode(Variable("Hello, world!", PrototypeType.STRING, 0), 0)

        val interpreter = Interpreter.InterpreterConstructor.create(V1())
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

        val interpreter = Interpreter.InterpreterConstructor.create(V1())
        interpreter.interpret(node5)
        var resultA: Any? = interpreter.getValue("a")
        var resultB: Any? = interpreter.getValue("b")
        println("a: $resultA")
        println("b: $resultB")
        assert(resultA == 2.0F)
        assert(resultB == 3.0F)
        // y se printea 5
    }

    @Test
    fun testConstAndBoolean() {
        // Statement: const b: boolean = true;
        val node6 = VariableDeclarationNode("b", "boolean", Variable("true", PrototypeType.BOOLEAN, 0), 0, false)

        val interpreter = Interpreter.InterpreterConstructor.create(V2())
        interpreter.interpret(node6)
        var result: Any? = interpreter.getValue("b")
        assert(result == true)
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
//        val interpreter = Interpreter.InterpreterConstructor.create(V2())
//        interpreter.interpret(node0)
//        interpreter.interpret(node8)
//        var result: Any? = interpreter.getValue("a")
//        println(result)
//        assert(result == "") //lo que vaya a ingresar en el readLine
//    }

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
//        val interpreter = Interpreter.InterpreterConstructor.create(V2())
//        interpreter.interpret(node9)
//    }

    @Test
    fun testSimpleIfBlock() {

        val node0 = VariableDeclarationNode("a", "string",Variable("a is 1", PrototypeType.STRING, 0))
        val node10 = IfNode(
            Variable("true", PrototypeType.BOOLEAN, 0),
            0,
            ParentNode(
                PrintNode(
                    Variable("a", PrototypeType.IDENTIFIER,0)
                )
            )
        )
        val interpreter = Interpreter.InterpreterConstructor.create(V2())
        interpreter.interpret(node0)
        interpreter.interpret(node10)
        var result = interpreter.getValue("a")
        println(result)
        assert(result == "a is 1")
    }

    @Test
    fun testMultipleBlock() {
        // Statement: if (true) { print("a is 1"); print("b is 2"); }
        val node0 = VariableDeclarationNode("a", "string",Variable("a is 1", PrototypeType.STRING, 0))
        val node1 = VariableDeclarationNode("b", "string",Variable("b is 2", PrototypeType.STRING, 0))
        val node11 = IfNode(
            Variable("true", PrototypeType.BOOLEAN, 0),
            0,
            ParentNode(
                listOf(
                    PrintNode(
                        Variable("a", PrototypeType.IDENTIFIER,0),
                        0
                    ),
                    PrintNode(
                        Variable("b", PrototypeType.IDENTIFIER,0),
                        0
                    )
                )
            )
        )
        val interpreter = Interpreter.InterpreterConstructor.create(V2())
        interpreter.interpret(node0)
        interpreter.interpret(node1)
        interpreter.interpret(node11)
        var resultA = interpreter.getValue("a")
        println(resultA)
        var resultB = interpreter.getValue("b")
        println(resultB)
        assert(resultA == "a is 1")
        assert(resultB == "b is 2")
        //funciona tambien
    }

    @Test
    fun testSimpleIfElseBlock() {
        // Statement: if (true) { print("a is 1"); } else { print("a is not 1"); }

        var node0 = VariableDeclarationNode("a", "boolean")
        var node1 = AssignmentNode("a", Variable("false", PrototypeType.BOOLEAN))
        val node12 = IfNode(
            Variable("a", PrototypeType.IDENTIFIER, 0),
            0,
            ParentNode(
                PrintNode(
                    Variable("a is 1", PrototypeType.STRING, 0)
                )
            ),
            ParentNode(
                PrintNode(
                    Variable("a is not 1", PrototypeType.STRING, 0)
                )
            )
        )
        val interpreter = Interpreter.InterpreterConstructor.create(V2())
        interpreter.interpret(node0)
        interpreter.interpret(node1)
        interpreter.interpret(node12)
    }

}

