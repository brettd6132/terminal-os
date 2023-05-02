import java.time.LocalDateTime

data class License(
    val key: String,
    val userIdentifier: String,
    val command: String,
    val issuedAt: LocalDateTime,
    var used: Boolean = false
)
