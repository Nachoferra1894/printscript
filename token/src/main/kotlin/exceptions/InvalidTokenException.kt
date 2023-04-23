package exceptions

import PrototypeType
import Token

class InvalidTokenException : Exception {
    constructor(token: Token) {
        throw Exception("Token: ${token.prototypeType} is incorrect at line ${token.line}")
    }
    constructor(prototypeType: PrototypeType) {
        throw Exception("Token: $prototypeType is incorrect")
    }
}
