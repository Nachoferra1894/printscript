# AST
The idea is for the *parser* to create a three of ASTNodes, one for each expression (declaration, assignment, etc).

Then, the *interpreter* will use the ASTNodeVisitor to know what to do with each ASTNode.