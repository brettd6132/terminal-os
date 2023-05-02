import java.io.File
import java.util.ServiceLoader

class CommandHandler {
    private val commands = mutableMapOf<String, Command>()

    fun registerCommand(name: String, command: Command) {
        commands[name] = command
    }

    fun registerCommandsInDirectory(directory: String) {
        val serviceLoader = ServiceLoader.load(Command::class.java)
        for (command in serviceLoader) {
            registerCommand(command::class.java.simpleName.toLowerCase(), command)
        }
        val directoryFile = File(directory)
        if (directoryFile.isDirectory) {
            val commandFiles = directoryFile.listFiles { file -> file.isFile && file.name.endsWith(".kt") }
            for (commandFile in commandFiles) {
                val commandName = commandFile.nameWithoutExtension
