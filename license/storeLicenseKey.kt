import java.io.File
import java.util.UUID

fun storeLicenseKey(licenseKey: String) {
    val filename = "${UUID.randomUUID()}.txt"
    val file = File("licenses/$filename")
    file.writeText(licenseKey)
}
