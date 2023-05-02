import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var input: String
    while (true) {
        print("> ")
        input = reader.readLine()
        if (input == "exit") {
            break
        } else {
            val commandArgs = input.split(" ")
            val commandName = commandArgs[0]
            val commandFile = File("commands/$commandName.kt")
            if (commandFile.exists()) {
                val commandClass = Class.forName("commands.$commandName")
                val command = commandClass.newInstance() as Command
                command.execute(commandArgs.drop(1))
            } else {
                when (commandName) {
                    "echo" -> {
                        println(commandArgs.drop(1).joinToString(" "))
                    }
                    "ls" -> {
                        val process = ProcessBuilder()
                                .command("ls", *commandArgs.drop(1).toTypedArray())
                                .redirectErrorStream(true)
                                .start()
                        val reader = BufferedReader(InputStreamReader(process.inputStream))
                        var line: String?
                        while (true) {
                            line = reader.readLine()
                            if (line == null) {
                                break
                            }
                            println(line)
                        }
                        process.waitFor()
                    }
                    else -> {
                        println("Command not recognized: $input")
                    }
                }
            }
        }
    }
}

interface Command {
    fun execute(args: List<String>)
}
