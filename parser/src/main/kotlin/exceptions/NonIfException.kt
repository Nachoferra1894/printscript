package exceptions

import Token

class NonIfException(token: Token) : Exception("Close bracket without if exception at line ${token.line}")
