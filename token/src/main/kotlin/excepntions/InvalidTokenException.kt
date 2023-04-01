package excepntions

import PrototypeType
import Token

class InvalidTokenException: Exception {
    constructor(token: Token){
        throw Exception("Token: $token is incorrect")
    }
    constructor(prototypeType: PrototypeType){
        throw Exception("Token: $prototypeType is incorrect")
    }

}
