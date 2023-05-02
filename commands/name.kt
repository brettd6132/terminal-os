import org.yaml.snakeyaml.Yaml
import java.io.File

class NameCommand : Command {
    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("Please specify your name.")
            return
        }
        val name = args[0]
        val configFile = File("config.yml")
        val yaml = Yaml()
        val config = if (configFile.exists()) {
            configFile.inputStream().use { input ->
                yaml.load(input)
            } as? Map<String, Any?> ?: emptyMap()
        } else {
            emptyMap()
        }
        config["name"] = name
        configFile.outputStream().use { output ->
            yaml.dump(config, output)
        }
        println("Name set to '$name'.")
    }
}
