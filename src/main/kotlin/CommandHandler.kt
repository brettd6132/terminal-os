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
                val commandName = commandFile.nameWithoutExtension.toLowerCase()
                try {
                    val commandClass = Class.forName("commands.$commandName").kotlin
                    val command = commandClass.objectInstance as? Command
                    if (command != null) {
                        registerCommand(commandName, command)
                    } else {
                        println("Failed to load command '$commandName': Could not create instance.")
                    }
                } catch (e: Exception) {
                    println("Failed to load command '$commandName': ${e.message}")
                }
            }
        }
    }

    fun executeCommand(input: String) {
        val parts = input.split(" ")
        if (parts.isEmpty()) {
            return
        }
        val commandName = parts[0].toLowerCase()
        val args = parts.subList(1, parts.size)
        val command = commands[commandName]
        if (command != null) {
            command.execute(args)
        } else {
            println("Command not found: '$commandName'.")
        }
    }
}
