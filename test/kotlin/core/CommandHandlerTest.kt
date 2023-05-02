import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CommandHandlerTest {
    private val commandHandler = CommandHandler()

    @Test
    fun `invalid`() {
        val result = assertFailsWith<IllegalArgumentException> {
            commandHandler.handleCommand("invalid")
        }
        assertEquals("Unknown command 'invalid'", result.message)
    }

    @Test
    fun `helloworld`() {
        val result = commandHandler.handleCommand("echo Hello, world!")
        assertEquals("Hello, world!", result)
    }
}
