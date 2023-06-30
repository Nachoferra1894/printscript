package implementation.v2

class InterpreterMapV2(private val map: MutableMap<String, ValueAndTypeV2>) {

    fun put(key: String, valueType: ValueAndTypeV2) = run { map[key] = valueType }

    fun getValue(key: String): ValueAndTypeV2 {
        return map[key] ?: throw Error(key + "not found")
    }

    fun exist(key: String): Boolean {
        return map.containsKey(key)
    }
}
