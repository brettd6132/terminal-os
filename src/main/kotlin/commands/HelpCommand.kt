import java.io.File
import java.util.Properties

class HelpCommand : Command {
    override fun execute(args: List<String>) {
        val envFile = File("help.env")
        val envProps = Properties()

        if (envFile.exists()) {
            envProps.load(envFile.inputStream())
        }

        val helpMessage = envProps.getProperty("HELP_MESSAGE") ?: "No help message configured"

        println(helpMessage)
    }
}
