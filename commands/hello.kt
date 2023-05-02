class HelloCommand : Command {
    override fun execute(args: List<String>) {
        println("Hello, ${args.firstOrNull() ?: "world"}!")
    }
}
