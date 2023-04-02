package fixtures

import Token
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

// Statement: a = 42;
val tokenList0 = listOf(
    Token(PrototypeType.IDENTIFIER, "a"),
    Token(PrototypeType.ASSIGNATION, null),
    Token(PrototypeType.NUMBER, "42"),
    Token(PrototypeType.SEMICOLON, null)
)
val node0 = AssignmentNode("a", Variable("42"))

// Statement: b = 22 + 20;
val tokenList1 = listOf(
    Token(PrototypeType.IDENTIFIER, "B"),
    Token(PrototypeType.ASSIGNATION, null),
    Token(PrototypeType.NUMBER, "22"),
    Token(PrototypeType.PLUS, null),
    Token(PrototypeType.NUMBER, "20"),
    Token(PrototypeType.SEMICOLON, null)
)
val node1 = AssignmentNode("B", Operation(Variable("22"), Operator.SUM, Variable("20")))

// Statement: let b: string = "Hello, world!";
val tokenList2 = listOf(
    Token(PrototypeType.LET, null),
    Token(PrototypeType.IDENTIFIER, "b"),
    Token(PrototypeType.COLON, null),
    Token(PrototypeType.STRING_TYPE, null),
    Token(PrototypeType.ASSIGNATION, null),
    Token(PrototypeType.STRING, "Hello, world!"),
    Token(PrototypeType.SEMICOLON, null)
)
val node2 = VariableDeclarationNode("b", "string", Variable("Hello, world!"))

// Statement: let c: number = 3 + 4 * 5;
val tokenList3 = listOf(
    Token(PrototypeType.LET, null),
    Token(PrototypeType.IDENTIFIER, "c"),
    Token(PrototypeType.COLON, null),
    Token(PrototypeType.NUMBER_TYPE, null),
    Token(PrototypeType.ASSIGNATION, null),
    Token(PrototypeType.NUMBER, "3"),
    Token(PrototypeType.PLUS, null),
    Token(PrototypeType.NUMBER, "4"),
    Token(PrototypeType.MULTIPLICATION, null),
    Token(PrototypeType.NUMBER, "5"),
    Token(PrototypeType.SEMICOLON, null)
)
val node3 = VariableDeclarationNode(
    "c",
    "number",
    Operation(Variable("3"), Operator.SUM, Operation(Variable("4"), Operator.MUL, Variable("5")))
)

// Statement: print("Hello, world!");
val tokenList4 = listOf(
    Token(PrototypeType.METHOD_PRINT, null),
    Token(PrototypeType.OPEN_PARENTHESIS, null),
    Token(PrototypeType.STRING, "Hello, world!"),
    Token(PrototypeType.CLOSE_PARENTHESIS, null),
    Token(PrototypeType.SEMICOLON, null)
)
val node4 = PrintNode(Variable("Hello, world!"))

// Statement: let a: number = 1 - 2 - 3;
// Statement: let b: number = a + 1;
// Statement: print(a + b);

val tokenList5 = listOf(
    Token(PrototypeType.LET, null),
    Token(PrototypeType.IDENTIFIER, "a"),
    Token(PrototypeType.COLON, null),
    Token(PrototypeType.NUMBER_TYPE, null),
    Token(PrototypeType.ASSIGNATION, null),
    Token(PrototypeType.NUMBER, "1"),
    Token(PrototypeType.SUBTRACTION, null),
    Token(PrototypeType.NUMBER, "2"),
    Token(PrototypeType.SUBTRACTION, null),
    Token(PrototypeType.NUMBER, "3"),
    Token(PrototypeType.SEMICOLON, null),

    Token(PrototypeType.LET, null),
    Token(PrototypeType.IDENTIFIER, "b"),
    Token(PrototypeType.COLON, null),
    Token(PrototypeType.NUMBER_TYPE, null),
    Token(PrototypeType.ASSIGNATION, null),
    Token(PrototypeType.IDENTIFIER, "a"),
    Token(PrototypeType.PLUS, null),
    Token(PrototypeType.NUMBER, "1"),
    Token(PrototypeType.SEMICOLON, null),

    Token(PrototypeType.METHOD_PRINT, null),
    Token(PrototypeType.OPEN_PARENTHESIS, null),
    Token(PrototypeType.IDENTIFIER, "a"),
    Token(PrototypeType.PLUS, null),
    Token(PrototypeType.IDENTIFIER, "b"),
    Token(PrototypeType.CLOSE_PARENTHESIS, null),
    Token(PrototypeType.SEMICOLON, null)
)
val node5 = ParentNode(
    listOf(
        VariableDeclarationNode("a", "number", Operation(Variable("1"), Operator.SUB, Operation(Variable("2"), Operator.SUB, Variable("3")))),
        VariableDeclarationNode("b", "number", Operation(Variable("a"), Operator.SUM, Variable("1"))),
        PrintNode(Operation(Variable("a"), Operator.SUM, Variable("b")))
    )
)
