import org.yaml.snakeyaml.Yaml
import java.io.File

object UserConfig {
    private val configFile = File("config/user.yml")
    private val yaml = Yaml()

    var name: String = ""
        set(value) {
            field = value
            save()
        }

    init {
        load()
    }

    private fun load() {
        if (configFile.exists()) {
            val data = configFile.readText()
            val map = yaml.load<Map<String, String>>(data)
            name = map["name"] ?: ""
        } else {
            save()
        }
    }

    private fun save() {
        val data = yaml.dump(mapOf("name" to name))
        configFile.parentFile.mkdirs()
        configFile.writeText(data)
    }
}
