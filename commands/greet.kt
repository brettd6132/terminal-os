class GreetCommand : Command {
    override fun execute(args: List<String>) {
        val name = args.firstOrNull() ?: "world"
        println("Greetings, $name!")
    }
}
