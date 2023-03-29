package excepntions

import Token

class InvalidTokenException(token: Token) : Exception("Token: $token is incorrect")
