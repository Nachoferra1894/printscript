package version

sealed interface Version

open class V1 : Version
open class V2 : Version

fun getVersionFromString(version: String): Version {
    return when (version) {
        "v1" -> V1()
        "v2" -> V2()
        else -> throw IllegalArgumentException("Unknown version: $version")
    }
}
