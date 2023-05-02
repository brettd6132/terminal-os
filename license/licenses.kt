import java.io.File
import java.time.LocalDate

class UserConfig(private val configFile: File) {
    private val config = mutableMapOf<String, String>()
    private val licenses = mutableMapOf<String, License>()

    init {
        configFile.createNewFile()
        configFile.readLines().forEach {
            val (key, value) = it.split("=")
            config[key] = value
        }
    }

    fun get(key: String): String? = config[key]

    fun set(key: String, value: String) {
        config[key] = value
        save()
    }

    fun addLicense(key: String, expirationDate: LocalDate) {
        licenses[key] = License(key, expirationDate)
        save()
    }

    fun getLicense(key: String): License? = licenses[key]

    fun useLicense(key: String) {
        licenses[key]?.used = true
        save()
    }

    private fun save() {
        configFile.writeText(config.map { "${it.key}=${it.value}" }.joinToString("\n"))
    }
}
