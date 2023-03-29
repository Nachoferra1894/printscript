package exceptions

import Token

class WrongTokenException(token: Token) : Exception("Token: ${token.prototypeType} not compatible with node")
