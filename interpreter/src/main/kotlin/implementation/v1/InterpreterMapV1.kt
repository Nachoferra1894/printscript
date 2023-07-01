package implementation.v1

class InterpreterMapV1(val map: MutableMap<String, ValueAndTypeV1>) {
    fun put(key: String, valueType: ValueAndTypeV1) = run { map[key] = valueType }

    fun getValue(key: String): ValueAndTypeV1 {
        return map[key] ?: throw Error(key + "not found")
    }

    fun exist(key: String): Boolean {
        return map.containsKey(key)
    }
}
