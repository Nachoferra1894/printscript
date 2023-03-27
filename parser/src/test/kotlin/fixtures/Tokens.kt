package fixtures

import Token
import expresions.Expression
import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import types.AssignmentNode
import types.VariableDeclaration

// Statement: a = 42;
val tokenList0 = listOf(
    Token(PrototypeType.IDENTIFIER, "a"),
    Token(PrototypeType.ASSIGNATION, null),
    Token(PrototypeType.NUMBER, "42")
)
val node0 = AssignmentNode("a", Variable("42"))

// Statement: 22 + 20;
val tokenList1 = listOf(
    Token(PrototypeType.NUMBER, "22"),
    Token(PrototypeType.PLUS, null),
    Token(PrototypeType.NUMBER, "20")
)
val node1 = Operation(Variable("22"), Operator.SUM, Variable("20"))

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
val node2 = VariableDeclaration("b", "string", Variable("Hello, world!"))

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
val node3 = VariableDeclaration(
    "c",
    "number",
    Operation(Variable("3"), Operator.SUM, Operation(Variable("3"), Operator.MUL, Variable("5")))
)