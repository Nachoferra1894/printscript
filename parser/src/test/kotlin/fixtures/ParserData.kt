// Statement: let a: number = 42;
val tokenList1 = listOf(
    Token(PrototypeType.LET, null),
    Token(PrototypeType.IDENTIFIER, "a"),
    Token(PrototypeType.COLON, null),
    Token(PrototypeType.NUMBER_TYPE, null),
    Token(PrototypeType.ASSIGNATION, null),
    Token(PrototypeType.NUMBER, "42"),
    Token(PrototypeType.SEMICOLON, null)
)

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

// Statement: let d: string = "Hello " + "world!";
val tokenList4 = listOf(
    Token(PrototypeType.LET, null),
    Token(PrototypeType.IDENTIFIER, "d"),
    Token(PrototypeType.COLON, null),
    Token(PrototypeType.STRING_TYPE, null),
    Token(PrototypeType.ASSIGNATION, null),
    Token(PrototypeType.STRING, "Hello "),
    Token(PrototypeType.PLUS, null),
    Token(PrototypeType.STRING, "world!"),
    Token(PrototypeType.SEMICOLON, null)
)

// Statement: let a: number = 1 - 2 - 3;
// Statement: let b: number = a + 1;
// Statement: print(a + b);


val tokenList7 = listOf(
    Token(PrototypeType.LET, null),
    Token(PrototypeType.IDENTIFIER, "g"),
    Token(PrototypeType.COLON, null),
    Token(PrototypeType.NUMBER_TYPE, null),
    Token(PrototypeType.ASSIGNATION, null),
    Token(PrototypeType.NUMBER, "1"),
    Token(PrototypeType.SUBTRACTION, null),
    Token(PrototypeType.NUMBER, "2"),
    Token(PrototypeType.SUBTRACTION, null),
    Token(PrototypeType.NUMBER, "3"),
    Token(PrototypeType.SEMICOLON, null)
)
