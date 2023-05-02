import java.io.FileInputStream
import java.util.*

object Config {
    private val properties = Properties()

    init {
        val input = FileInputStream(".env")
        properties.load(input)
    }

    fun get(name: String): String? {
        return properties.getProperty(name)
    }
}
