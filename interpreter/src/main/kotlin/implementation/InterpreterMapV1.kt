package implementation

class InterpreterMapV1(private val map: MutableMap<String, ValueAndTypeV1>) {
    fun put(key: String, valueType: ValueAndTypeV1) = run { map[key] = valueType }

    fun getValue(key: String): ValueAndTypeV1 {
        return map[key] ?: throw Error(key + "not found")
    }

    fun exist(key: String): Boolean {
        return map.containsKey(key)
    }

}
