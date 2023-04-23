package fixtures

import Token
import expresions.Operator
import expresions.types.Operation
import expresions.types.ReadInputExp
import expresions.types.Variable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode
import version.V2

fun getFlowFromTokenList(tokenList: List<Token>): Flow<Token> {
    return flow {
        tokenList.forEach { token ->
            emit(token)
        }
    }
}

// Statement: a = 42;
val tokenList0 = listOf(
    Token(PrototypeType.IDENTIFIER, "a", 0, 1, 0),
    Token(PrototypeType.EQUALS, null, 2, 3, 0),
    Token(PrototypeType.NUMBER, "42", 4, 6, 0),
    Token(PrototypeType.SEMICOLON, null, 6, 7, 0)
)
val node0 = AssignmentNode("a", Variable("42", PrototypeType.NUMBER, 0), 1)

// Statement: b = 22 + 20;
val tokenList1 = listOf(
    Token(PrototypeType.IDENTIFIER, "B", 0, 1, 0),
    Token(PrototypeType.EQUALS, null, 2, 3, 0),
    Token(PrototypeType.NUMBER, "22", 4, 6, 0),
    Token(PrototypeType.PLUS, null, 7, 8, 0),
    Token(PrototypeType.NUMBER, "20", 9, 12, 0),
    Token(PrototypeType.SEMICOLON, null, 12, 13, 0)
)
val node1 = AssignmentNode(
    "B",
    Operation(Variable("22", PrototypeType.NUMBER, 0), Operator.SUM, Variable("20", PrototypeType.NUMBER, 0), 0),
    0
)

// Statement: let b: string = "Hello, world!";
val tokenList2 = listOf(
    Token(PrototypeType.LET, null, 0, 3, 0),
    Token(PrototypeType.IDENTIFIER, "b", 4, 5, 0),
    Token(PrototypeType.COLON, null, 5, 6, 0),
    Token(PrototypeType.STRING_TYPE, null, 7, 13, 0),
    Token(PrototypeType.EQUALS, null, 14, 15, 0),
    Token(PrototypeType.STRING, "Hello, world!", 16, 29, 0),
    Token(PrototypeType.SEMICOLON, null, 29, 30, 0)
)
val node2 = VariableDeclarationNode("b", "string", Variable("Hello, world!", PrototypeType.STRING, 0), 0)

// Statement: let c: number = 3 + 4 * 5;
val tokenList3 = listOf(
    Token(PrototypeType.LET, null, 0, 3, 0),
    Token(PrototypeType.IDENTIFIER, "c", 4, 5, 0),
    Token(PrototypeType.COLON, null, 5, 6, 0),
    Token(PrototypeType.NUMBER_TYPE, null, 7, 13, 0),
    Token(PrototypeType.EQUALS, null, 14, 15, 0),
    Token(PrototypeType.NUMBER, "3", 16, 17, 0),
    Token(PrototypeType.PLUS, null, 18, 19, 0),
    Token(PrototypeType.NUMBER, "4", 20, 21, 0),
    Token(PrototypeType.MULTIPLICATION, null, 22, 23, 0),
    Token(PrototypeType.NUMBER, "5", 24, 25, 0),
    Token(PrototypeType.SEMICOLON, null, 25, 26, 0)
)
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

// Statement: print("Hello, world!");
val tokenList4 = listOf(
    Token(PrototypeType.METHOD_PRINT, null, 0, 5, 0),
    Token(PrototypeType.OPEN_PARENTHESIS, null, 5, 6, 0),
    Token(PrototypeType.STRING, "Hello, world!", 6, 19, 0),
    Token(PrototypeType.CLOSE_PARENTHESIS, null, 19, 20, 0),
    Token(PrototypeType.SEMICOLON, null, 20, 21, 0)
)
val node4 = PrintNode(Variable("Hello, world!", PrototypeType.STRING, 0), 0)

// Statement: let a: number = 1 - 2 - 3;
// Statement: let b: number;
// Statement: b = a + 1;
// Statement: print(a + b);

val tokenList5 = listOf(
    Token(PrototypeType.LET, null, 0, 3, 0),
    Token(PrototypeType.IDENTIFIER, "a", 4, 5, 0),
    Token(PrototypeType.COLON, null, 5, 6, 0),
    Token(PrototypeType.NUMBER_TYPE, null, 7, 13, 0),
    Token(PrototypeType.EQUALS, null, 14, 15, 0),
    Token(PrototypeType.NUMBER, "1", 16, 17, 0),
    Token(PrototypeType.SUBTRACTION, null, 18, 19, 0),
    Token(PrototypeType.NUMBER, "2", 20, 21, 0),
    Token(PrototypeType.SUBTRACTION, null, 22, 23, 0),
    Token(PrototypeType.NUMBER, "3", 24, 25, 0),
    Token(PrototypeType.SEMICOLON, null, 25, 26, 0),

    Token(PrototypeType.LET, null, 0, 3, 1),
    Token(PrototypeType.IDENTIFIER, "b", 4, 5, 1),
    Token(PrototypeType.COLON, null, 5, 6, 1),
    Token(PrototypeType.NUMBER_TYPE, null, 7, 13, 1),
    Token(PrototypeType.SEMICOLON, null, 13, 14, 1),

    Token(PrototypeType.IDENTIFIER, "b", 0, 1, 2),
    Token(PrototypeType.EQUALS, null, 2, 4, 2),
    Token(PrototypeType.IDENTIFIER, "a", 5, 6, 2),
    Token(PrototypeType.PLUS, null, 7, 8, 2),
    Token(PrototypeType.NUMBER, "1", 9, 10, 2),
    Token(PrototypeType.SEMICOLON, null, 10, 11, 2),

    Token(PrototypeType.METHOD_PRINT, null, 0, 7, 3),
    Token(PrototypeType.OPEN_PARENTHESIS, null, 7, 8, 3),
    Token(PrototypeType.IDENTIFIER, "a", 9, 10, 3),
    Token(PrototypeType.PLUS, null, 11, 12, 3),
    Token(PrototypeType.IDENTIFIER, "b", 13, 14, 3),
    Token(PrototypeType.CLOSE_PARENTHESIS, null, 14, 15, 3),
    Token(PrototypeType.SEMICOLON, null, 15, 16, 3)
)
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

// Statement: const b: boolean = true;
val tokenList6 = listOf(
    Token(PrototypeType.CONST, null, 0, 4, 0),
    Token(PrototypeType.IDENTIFIER, "b", 5, 6, 0),
    Token(PrototypeType.COLON, null, 7, 8, 0),
    Token(PrototypeType.BOOLEAN_TYPE, null, 9, 15, 0),
    Token(PrototypeType.EQUALS, null, 16, 17, 0),
    Token(PrototypeType.BOOLEAN, "true", 18, 22, 0),
    Token(PrototypeType.SEMICOLON, null, 22, 23, 0)
)
val node6 = VariableDeclarationNode("b", "boolean", Variable("true", PrototypeType.BOOLEAN, 0, V2()), 0, false)
val mutableNode6 = VariableDeclarationNode("b", "boolean", Variable("true", PrototypeType.BOOLEAN, 0, V2()))

// Statement: a =    42;
val tokenList7 = listOf(
    Token(PrototypeType.IDENTIFIER, "a", 0, 1, 1),
    Token(PrototypeType.EQUALS, null, 2, 3, 1),
    Token(PrototypeType.SPACE, null, 4, 5, 1),
    Token(PrototypeType.SPACE, null, 6, 7, 1),
    Token(PrototypeType.SPACE, null, 8, 9, 1),
    Token(PrototypeType.NUMBER, "42", 9, 11, 1),
    Token(PrototypeType.SEMICOLON, null, 12, 13, 1)
)
val node7 = AssignmentNode("a", Variable("42", PrototypeType.NUMBER, 1), 1)

// Statement: a = readInput("Enter a number: ");
val tokenList8 = listOf(
    Token(PrototypeType.IDENTIFIER, "a", 0, 1, 1),
    Token(PrototypeType.EQUALS, null, 2, 3, 1),
    Token(PrototypeType.SPACE, null, 4, 5, 1),
    Token(PrototypeType.METHOD_READ_INPUT, null, 6, 15, 1),
    Token(PrototypeType.OPEN_PARENTHESIS, null, 15, 16, 1),
    Token(PrototypeType.STRING, "Enter a number: ", 16, 32, 1),
    Token(PrototypeType.CLOSE_PARENTHESIS, null, 32, 33, 1),
    Token(
        PrototypeType.SEMICOLON,
        null,
        34,
        35,
        1
    )
)
val node8 = AssignmentNode(
    "a",
    ReadInputExp(
        Variable("Enter a number: ", PrototypeType.STRING, 0),
        0
    ),
    0
)

// Statement: let a: string = readInput(a+b);
val tokenList9 = listOf(
    Token(PrototypeType.LET, null, 0, 3, 0),
    Token(PrototypeType.IDENTIFIER, "a", 4, 5, 0),
    Token(PrototypeType.COLON, null, 5, 6, 0),
    Token(PrototypeType.STRING_TYPE, null, 7, 12, 0),
    Token(PrototypeType.EQUALS, null, 13, 14, 0),
    Token(PrototypeType.METHOD_READ_INPUT, null, 15, 24, 0),
    Token(PrototypeType.OPEN_PARENTHESIS, null, 24, 25, 0),
    Token(PrototypeType.IDENTIFIER, "a", 25, 26, 0),
    Token(PrototypeType.PLUS, null, 26, 27, 0),
    Token(PrototypeType.IDENTIFIER, "b", 27, 28, 0),
    Token(PrototypeType.CLOSE_PARENTHESIS, null, 28, 29, 0),
    Token(PrototypeType.SEMICOLON, null, 29, 30, 0)
)
val node9 = VariableDeclarationNode(
    "a",
    "string",
    ReadInputExp(
        Operation(
            Variable("a", PrototypeType.IDENTIFIER, 0),
            Operator.SUM,
            Variable("b", PrototypeType.IDENTIFIER, 0),
            0
        ),
        0
    ),
    0
)

// // Statement: if (true) { print("a is 1"); }
// val tokenList10 = listOf(
//    Token(PrototypeType.IF, null, 0, 2, 0),
//    Token(PrototypeType.OPEN_PARENTHESIS, null, 3, 4, 0),
//    Token(PrototypeType.BOOLEAN, "true", 4, 8, 0),
//    Token(PrototypeType.CLOSE_PARENTHESIS, null, 8, 9, 0),
//    Token(PrototypeType.OPEN_BRACE, null, 10, 11, 0),
//    Token(PrototypeType.METHOD_PRINT, null, 12, 17, 0),
//    Token(PrototypeType.OPEN_PARENTHESIS, null, 17, 18, 0),
//    Token(PrototypeType.STRING, "a is 1", 18, 24, 0),
//    Token(PrototypeType.CLOSE_PARENTHESIS, null, 24, 25, 0),
//    Token(PrototypeType.SEMICOLON, null, 25, 26, 0),
//    Token(PrototypeType.CLOSE_BRACE, null, 27, 28, 0)
// )
// val node10 = IfNode(
//    Variable("true", PrototypeType.BOOLEAN, 0,V2()),
//    ParentNode(
//        PrintNode(
//            Variable("a is 1", PrototypeType.STRING, 0),
//            0
//        )
//    ),
//    0
// )
