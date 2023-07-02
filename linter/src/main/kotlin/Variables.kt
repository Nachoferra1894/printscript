import expresions.Operator
import expresions.types.Operation
import expresions.types.Variable
import types.AssignmentNode
import types.ParentNode
import types.PrintNode
import types.VariableDeclarationNode

val node = ParentNode(
    listOf(
        VariableDeclarationNode(
            "a",
            "number",
            Operation(
                Variable("1", PrototypeType.NUMBER, 1),
                Operator.SUB,
                Operation(
                    Variable("2", PrototypeType.NUMBER, 1),
                    Operator.SUB,
                    Variable("3", PrototypeType.NUMBER, 1),
                    1
                ),
                1
            ),
            1
        ),
        VariableDeclarationNode("b", "number", 2),
        AssignmentNode("b", Operation(Variable("a", PrototypeType.IDENTIFIER, 3), Operator.SUM, Variable("1", PrototypeType.NUMBER, 3), 3), 3),
        PrintNode(Variable("a", PrototypeType.IDENTIFIER, 4), 4)
    )
)