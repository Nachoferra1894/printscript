package configuration

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConfigTest {

    @Test
    fun testRead() {
        val readConfig = ReadConfig()
        readConfig.getJsonDataFromAsset()
        val configs = readConfig.getConfigClasses()
        assertEquals(6, configs.size)
    }
}
