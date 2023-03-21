# AST
The idea is for the *parser* to create a three of ASTNodes, one for each expression (declaration, assignment, etc).

Then, the *interpreter* will use the ASTNodeVisitor to know what to do with each ASTNode.

When assigning a value to a variable, it can be done in many ways:

- x = 5;
- y = x;
- z = x + y;

To solve this, expressions will be used. An expression consists of two expressions and an operand:

expression = expression - operand - expression.

Expressions behave similar to nodes, they have a visitor that visits the type of expression 