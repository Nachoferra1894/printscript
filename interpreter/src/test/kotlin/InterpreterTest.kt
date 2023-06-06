import expresions.types.Variable
import implementation.Interpreter
import implementation.InterpreterVisitorV2
import implementation.ValueAndTypeV2
import org.junit.jupiter.api.Test
import types.*
import version.V1

class InterpreterTest {

    @Test
    fun testSimpleAssignationToken() {
        // Statement: a = 42;
        val node0 = VariableDeclarationNode("a", "number")
        val node1 = AssignmentNode("a", Variable("42", PrototypeType.NUMBER, 0), 1)

        val interpreter = Interpreter.InterpreterConstructor.create(V1())
        interpreter.interpret(node0)
        interpreter.interpret(node1)
        var memory: InterpreterVisitorV2 = interpreter.getMemory() as InterpreterVisitorV2
        assert(memory.map.getValue("a").value == ValueAndTypeV2("42","number",true))
    }

//    @Test
//    fun testSimpleSumToken() {
//        // Statement: b = 22 + 20;
//        val node0 = VariableDeclarationNode("B", "number")
//        val node1 = AssignmentNode(
//            "B",
//            Operation(Variable("22", PrototypeType.NUMBER, 0), Operator.SUM, Variable("20", PrototypeType.NUMBER, 0), 0),
//            0
//        )
//
//        val interpreter = Interpreter.InterpreterConstructor.create(V1())
//        interpreter.interpret(node0)
//        interpreter.interpret(node1)
//        assert(interpreter.getMemory().getValue("B").value == ValueAndType("42","number",true))
//    }
//
//    @Test
//    fun testDeclarationForString() {
//        // Statement: let b: string = "Hello, world!";
//        val node2 = VariableDeclarationNode("b", "string", Variable("Hello, world!", PrototypeType.STRING, 0), 0)
//
//        val interpreter = Interpreter.InterpreterConstructor.create(V1())
//        interpreter.interpret(node2)
//        assert(interpreter.getMemory().getValue("b").value == ValueAndType("Hello, world!","string",true))
//    }
//
//    @Test
//    fun testDeclarationForIntAndExpression() {
//        // Statement: let c: number = 3 + 4 * 5;
//        val node3 = VariableDeclarationNode(
//            "c",
//            "number",
//            Operation(
//                Variable("3", PrototypeType.NUMBER, 0),
//                Operator.SUM,
//                Operation(Variable("4", PrototypeType.NUMBER, 0), Operator.MUL, Variable("5", PrototypeType.NUMBER, 0), 0),
//                0
//            ),
//            0
//        )
//
//        val interpreter = Interpreter.InterpreterConstructor.create(V1())
//        interpreter.interpret(node3)
//        assert(interpreter.getMemory().getValue("c").value == ValueAndType("23","number",true))
//    }
//
////    @Test
////    fun testPrint() {
////        // Statement: print("Hello, world!");
////        val node4 = PrintNode(Variable("Hello, world!", PrototypeType.STRING, 0), 0)
////
////        val interpreter = Interpreter.InterpreterConstructor.create(V1())
////        interpreter.interpret(node4)
////        assert()
////    }
//
//    @Test
//    fun testMultipleLines() {
//        // Statement: let a: number = 1 - 2 - 3;
//        // Statement: let b: number;
//        // Statement: b = a + 1;
//        // Statement: print(a + b);
//        val node5 = ParentNode(
//            listOf(
//                VariableDeclarationNode(
//                    "a",
//                    "number",
//                    Operation(
//                        Variable("1", PrototypeType.NUMBER, 0),
//                        Operator.SUB,
//                        Operation(
//                            Variable("2", PrototypeType.NUMBER, 0),
//                            Operator.SUB,
//                            Variable("3", PrototypeType.NUMBER, 0),
//                            0
//                        ),
//                        0
//                    ),
//                    0
//                ),
//                VariableDeclarationNode("b", "number", 1),
//                AssignmentNode(
//                    "b",
//                    Operation(
//                        Variable("a", PrototypeType.IDENTIFIER, 2),
//                        Operator.SUM,
//                        Variable("1", PrototypeType.NUMBER, 2),
//                        2
//                    ),
//                    2
//                ),
//                PrintNode(
//                    Operation(
//                        Variable("a", PrototypeType.IDENTIFIER, 3),
//                        Operator.SUM,
//                        Variable("b", PrototypeType.IDENTIFIER, 3),
//                        3
//                    ),
//                    3
//                )
//            )
//        )
//
//        val interpreter = Interpreter.InterpreterConstructor.create(V1())
//        interpreter.interpret(node5)
//        assert(interpreter.getMemory().getValue("a").value == ValueAndType("0","number",true))
//        assert(interpreter.getMemory().getValue("b").value == ValueAndType("1","number",true))
//    }
//
//    @Test
//    fun testConstAndBoolean() {
//        // Statement: const b: boolean = true;
//        val node6 = VariableDeclarationNode("b", "boolean", Variable("true", PrototypeType.BOOLEAN, 0), 0, false)
//
//        val interpreter = Interpreter.InterpreterConstructor.create(V2())
//        interpreter.interpret(node6)
//        assert(interpreter.getMemory().getValue("b").value == ValueAndType("true","boolean",false))
//    }

//    @Test
//    fun testReadInput() {
//        // Statement: a = readInput("Enter a number: ");
//
//        val node8 = AssignmentNode(
//            "a",
//            ReadInputExp(
//                Variable("Enter a number: ", PrototypeType.STRING, 0),
//                0
//            ),
//            0
//        )
//
//        val interpreter = Interpreter.InterpreterConstructor.create(V1())
//        interpreter.interpret(node8)
//        assert()
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
//        assert()
//    }

//    @Test
//    fun testSimpleIfBlock() {
//        // Statement: let a: string = readInput(a+b);
//
//        val node10 = IfNode(
//            Variable("true", PrototypeType.BOOLEAN, 0),
//            0,
//            ParentNode(
//                PrintNode(
//                    Variable("a is 1", PrototypeType.STRING, 0)
//                )
//            )
//        )
//        val interpreter = Interpreter.InterpreterConstructor.create(V2())
//        interpreter.interpret(node10)
//        assert()
//    }

//    @Test
//    fun testMultipleBlock() {
//        // Statement: if (true) { print("a is 1"); print("b is 2"); }
//        val node11 = IfNode(
//            Variable("true", PrototypeType.BOOLEAN, 0),
//            0,
//            ParentNode(
//                listOf(
//                    PrintNode(
//                        Variable("a is 1", PrototypeType.STRING, 0),
//                        0
//                    ),
//                    PrintNode(
//                        Variable("b is 2", PrototypeType.STRING, 0),
//                        0
//                    )
//                )
//            )
//        )
//        val interpreter = Interpreter.InterpreterConstructor.create(V2())
//        interpreter.interpret(node11)
//        assert()
//    }

//    @Test
//    fun testSimpleIfElseBlock() {
//        // Statement: if (true) { print("a is 1"); } else { print("a is not 1"); }
//
//        val node12 = IfNode(
//            Variable("a", PrototypeType.IDENTIFIER, 0),
//            0,
//            ParentNode(
//                PrintNode(
//                    Variable("a is 1", PrototypeType.STRING, 0)
//                )
//            ),
//            ParentNode(
//                PrintNode(
//                    Variable("a is not 1", PrototypeType.STRING, 0)
//                )
//            )
//        )
//        val interpreter = Interpreter.InterpreterConstructor.create(V2())
//        interpreter.interpret(node12)
//        assert()
//    }
}

