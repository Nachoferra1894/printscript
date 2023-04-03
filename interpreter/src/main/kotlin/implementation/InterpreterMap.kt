package implementation

class InterpreterMap (private val map: MutableMap<String, Any>) {

    fun put(key: String, value: Any) = run { map[key] = value }

    fun getValue(key: String): Any {
        return map[key] ?: throw Error(key + "not found")
    }

}