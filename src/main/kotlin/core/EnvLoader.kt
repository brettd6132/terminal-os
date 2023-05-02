import java.io.File
import java.io.FileInputStream
import java.util.*

object EnvLoader {
    private val properties = Properties()

    init {
        val envFile = File(".env")
        if (envFile.exists()) {
            val inputStream = FileInputStream(envFile)
            properties.load(inputStream)
        }
    }

    fun get(key: String): String? {
        return properties.getProperty(key)
    }
}
