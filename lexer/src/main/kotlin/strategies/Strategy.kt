package strategies

import Token

interface Strategy {
    fun isStrategy(line: String, index: Int): Boolean
    fun getToken(line: String, index: Int): Token
}
