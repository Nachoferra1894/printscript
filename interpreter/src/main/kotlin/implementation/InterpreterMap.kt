package implementation

class InterpreterMap(private val map: MutableMap<String, ValueAndType>) {

    fun put(key: String, valueType: ValueAndType) = run { map[key] = valueType }

    fun getValue(key: String): ValueAndType {
        return map[key] ?: throw Error(key + "not found")
    }

    fun exist(key: String): Boolean {
        return map.containsKey(key)
    }
}
