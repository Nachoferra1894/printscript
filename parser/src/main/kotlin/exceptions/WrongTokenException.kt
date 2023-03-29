package exceptions

import Token
import interfaces.ASTNode

class WrongTokenException(token: Token,nodeType: ASTNode) : Exception("Token: $token not compatible with node $nodeType")