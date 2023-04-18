// Statement: let a: number = 42;
private val tokenList1 = listOf(
    Token(PrototypeType.LET, null, 0, 3, 1),
    Token(PrototypeType.IDENTIFIER, "a", 4, 5, 1),
    Token(PrototypeType.COLON, null, 5, 6, 1),
    Token(PrototypeType.NUMBER_TYPE, null, 7, 13, 1),
    Token(PrototypeType.ASSIGNATION, null, 14, 15, 1),
    Token(PrototypeType.NUMBER, "42", 16, 18, 1),
    Token(PrototypeType.SEMICOLON, null, 18, 19, 1)
)

// Statement: let b: string = "Hello, world!";
private val tokenList2 = listOf(
    Token(PrototypeType.LET, null, 0, 3, 1),
    Token(PrototypeType.IDENTIFIER, "b", 4, 5, 1),
    Token(PrototypeType.COLON, null, 5, 6, 1),
    Token(PrototypeType.STRING_TYPE, null, 7, 13, 1),
    Token(PrototypeType.ASSIGNATION, null, 14, 15, 1),
    Token(PrototypeType.STRING, "Hello, world!", 16, 28, 1),
    Token(PrototypeType.SEMICOLON, null, 28, 29, 1)
)

// Statement: let c: number = 3 + 4 * 5;
private val tokenList3 = listOf(
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

// Statement: let d: string = "Hello " + "world!";
private val tokenList4 = listOf(
    Token(PrototypeType.LET, null, 0, 3, 1),
    Token(PrototypeType.IDENTIFIER, "d", 4, 5, 1),
    Token(PrototypeType.COLON, null, 5, 6, 1),
    Token(PrototypeType.STRING_TYPE, null, 7, 13, 1),
    Token(PrototypeType.ASSIGNATION, null, 14, 15, 1),
    Token(PrototypeType.STRING, "Hello ", 16, 21, 1),
    Token(PrototypeType.PLUS, null, 22, 23, 1),
    Token(PrototypeType.STRING, "world!", 24, 29, 1),
    Token(PrototypeType.SEMICOLON, null, 29, 30, 1)
)

// Statement: let a: number = 1 - 2 - 3;
// Statement: let b: number = a + 1;
// Statement: print(a + b); // 3

private val tokenList7 = listOf(
    Token(PrototypeType.LET, null, 0, 3, 1),
    Token(PrototypeType.IDENTIFIER, "g", 4, 5, 1),
    Token(PrototypeType.COLON, null, 5, 6, 1),
    Token(PrototypeType.NUMBER_TYPE, null, 7, 13, 1),
    Token(PrototypeType.ASSIGNATION, null, 14, 15, 1),
    Token(PrototypeType.NUMBER, "1", 16, 17, 1),
    Token(PrototypeType.SUBTRACTION, null, 18, 19, 1),
    Token(PrototypeType.NUMBER, "2", 20, 21, 1),
    Token(PrototypeType.SUBTRACTION, null, 22, 23, 1),
    Token(PrototypeType.NUMBER, "3", 24, 25, 1),
    Token(PrototypeType.SEMICOLON, null, 25, 26, 1)
)
