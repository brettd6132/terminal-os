import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

class UserConfig(private val configPath: String) {
    private val properties = Properties()

    init {
        val configFile = File(configPath)
        if (configFile.exists()) {
            val input = FileInputStream(configPath)
            properties.load(input)
            input.close()
        }
    }

    fun getValue(key: String, defaultValue: String = ""): String {
        return properties.getProperty(key, defaultValue)
    }

    fun setValue(key: String, value: String) {
        properties.setProperty(key, value)
        val output = FileOutputStream(configPath)
        properties.store(output, null)
        output.close()
    }
}
