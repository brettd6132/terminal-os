import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

class InputDriver {
    private val inputReader = BufferedReader(InputStreamReader(System.`in`))

    suspend fun readLine(): String = withContext(Dispatchers.IO) {
        inputReader.readLine()
    }
}
