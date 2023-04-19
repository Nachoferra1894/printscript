package fixtures

import Token
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

// Statement: a = 42;
val tokenList0 = listOf(
    Token(PrototypeType.IDENTIFIER, "a", 0, 1, 1),
    Token(PrototypeType.ASSIGNATION, null, 2, 3, 1),
    Token(PrototypeType.NUMBER, "42", 4, 6, 1),
    Token(PrototypeType.SEMICOLON, null, 6, 7, 1)
)
val node0 = AssignmentNode("a", Variable("42", PrototypeType.NUMBER, 1), 1)

// Statement: b = 22 + 20;
val tokenList1 = listOf(
    Token(PrototypeType.IDENTIFIER, "B", 0, 1, 1),
    Token(PrototypeType.ASSIGNATION, null, 2, 3, 1),
    Token(PrototypeType.NUMBER, "22", 4, 6, 1),
    Token(PrototypeType.PLUS, null, 7, 8, 1),
    Token(PrototypeType.NUMBER, "20", 9, 12, 1),
    Token(PrototypeType.SEMICOLON, null, 12, 13, 1)
)
val node1 = AssignmentNode("B", Operation(Variable("22", PrototypeType.NUMBER, 1), Operator.SUM, Variable("20", PrototypeType.NUMBER, 1), 1), 1)

// Statement: let b: string = "Hello, world!";
val tokenList2 = listOf(
    Token(PrototypeType.LET, null, 0, 3, 1),
    Token(PrototypeType.IDENTIFIER, "b", 4, 5, 1),
    Token(PrototypeType.COLON, null, 5, 6, 1),
    Token(PrototypeType.STRING_TYPE, null, 7, 13, 1),
    Token(PrototypeType.ASSIGNATION, null, 14, 15, 1),
    Token(PrototypeType.STRING, "Hello, world!", 16, 29, 1),
    Token(PrototypeType.SEMICOLON, null, 29, 30, 1)
)
val node2 = VariableDeclarationNode("b", "string", Variable("Hello, world!", PrototypeType.STRING, 1), 1)

// Statement: let c: number = 3 + 4 * 5;
val tokenList3 = listOf(
    Token(PrototypeType.LET, null, 0, 3, 1),
    Token(PrototypeType.IDENTIFIER, "c", 4, 5, 1),
    Token(PrototypeType.COLON, null, 5, 6, 1),
    Token(PrototypeType.NUMBER_TYPE, null, 7, 13, 1),
    Token(PrototypeType.ASSIGNATION, null, 14, 15, 1),
    Token(PrototypeType.NUMBER, "3", 16, 17, 1),
    Token(PrototypeType.PLUS, null, 18, 19, 1),
    Token(PrototypeType.NUMBER, "4", 20, 21, 1),
    Token(PrototypeType.MULTIPLICATION, null, 22, 23, 1),
    Token(PrototypeType.NUMBER, "5", 24, 25, 1),
    Token(PrototypeType.SEMICOLON, null, 25, 26, 1)
)
val node3 = VariableDeclarationNode(
    "c",
    "number",
    Operation(Variable("3", PrototypeType.NUMBER, 1), Operator.SUM, Operation(Variable("4", PrototypeType.NUMBER, 1), Operator.MUL, Variable("5", PrototypeType.NUMBER, 1), 1), 1),
    1
)

// Statement: print("Hello, world!");
val tokenList4 = listOf(
    Token(PrototypeType.METHOD_PRINT, null, 0, 5, 1),
    Token(PrototypeType.OPEN_PARENTHESIS, null, 5, 6, 1),
    Token(PrototypeType.STRING, "Hello, world!", 6, 19, 1),
    Token(PrototypeType.CLOSE_PARENTHESIS, null, 19, 20, 1),
    Token(PrototypeType.SEMICOLON, null, 20, 21, 1)
)
val node4 = PrintNode(Variable("Hello, world!", PrototypeType.STRING, 1), 1)

// Statement: let a: number = 1 - 2 - 3;
// Statement: let b: number;
// Statement: b = a + 1;
// Statement: print(a + b);

val tokenList5 = listOf(
    Token(PrototypeType.LET, null, 0, 3, 1),
    Token(PrototypeType.IDENTIFIER, "a", 4, 5, 1),
    Token(PrototypeType.COLON, null, 5, 6, 1),
    Token(PrototypeType.NUMBER_TYPE, null, 7, 13, 1),
    Token(PrototypeType.ASSIGNATION, null, 14, 15, 1),
    Token(PrototypeType.NUMBER, "1", 16, 17, 1),
    Token(PrototypeType.SUBTRACTION, null, 18, 19, 1),
    Token(PrototypeType.NUMBER, "2", 20, 21, 1),
    Token(PrototypeType.SUBTRACTION, null, 22, 23, 1),
    Token(PrototypeType.NUMBER, "3", 24, 25, 1),
    Token(PrototypeType.SEMICOLON, null, 25, 26, 1),

    Token(PrototypeType.LET, null, 0, 3, 2),
    Token(PrototypeType.IDENTIFIER, "b", 4, 5, 2),
    Token(PrototypeType.COLON, null, 5, 6, 2),
    Token(PrototypeType.NUMBER_TYPE, null, 7, 13, 2),
    Token(PrototypeType.SEMICOLON, null, 13, 14, 2),

    Token(PrototypeType.IDENTIFIER, "b", 0, 1, 3),
    Token(PrototypeType.ASSIGNATION, null, 2, 4, 3),
    Token(PrototypeType.IDENTIFIER, "a", 5, 6, 3),
    Token(PrototypeType.PLUS, null, 7, 8, 3),
    Token(PrototypeType.NUMBER, "1", 9, 10, 3),
    Token(PrototypeType.SEMICOLON, null, 10, 11, 3),

    Token(PrototypeType.METHOD_PRINT, null, 0, 7, 4),
    Token(PrototypeType.OPEN_PARENTHESIS, null, 7, 8, 4),
    Token(PrototypeType.IDENTIFIER, "a", 9, 10, 4),
    Token(PrototypeType.PLUS, null, 11, 12, 4),
    Token(PrototypeType.IDENTIFIER, "b", 13, 14, 4),
    Token(PrototypeType.CLOSE_PARENTHESIS, null, 14, 15, 4),
    Token(PrototypeType.SEMICOLON, null, 15, 16, 4)
)
val node5 = ParentNode(
    listOf(
        VariableDeclarationNode("a", "number", Operation(Variable("1", PrototypeType.NUMBER, 1), Operator.SUB, Operation(Variable("2", PrototypeType.NUMBER, 1), Operator.SUB, Variable("3", PrototypeType.NUMBER, 1), 1), 1), 1),
        VariableDeclarationNode("b", "number", 2),
        AssignmentNode("b", Operation(Variable("a", PrototypeType.IDENTIFIER, 3), Operator.SUM, Variable("1", PrototypeType.NUMBER, 3), 3), 3),
        PrintNode(Operation(Variable("a", PrototypeType.IDENTIFIER, 4), Operator.SUM, Variable("b", PrototypeType.IDENTIFIER, 4), 4), 4)
    )
)

fun getFlowFromTokenList(tokenList: List<Token>): Flow<Token> {
    return flow {
        tokenList.forEach { token ->
            emit(token)
        }
    }
}
