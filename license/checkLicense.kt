import java.io.File
import java.util.UUID

fun checkLicense(commandName: String, licenseKey: String): Boolean {
    // Read the user.yml file to get the user's license keys
    val userFile = File("user.yml")
    val userConfig = YamlConfiguration.loadConfiguration(userFile)
    val licenses = userConfig.getStringList("licenses")

    // Check if the license key is valid and mark it as used if it is
    if (licenses.contains(licenseKey)) {
        // Remove the license key from the list to mark it as used
        licenses.remove(licenseKey)
        userConfig.set("licenses", licenses)
        userConfig.save(userFile)

        // Allow the user to run the command
        println("Running licensed command: $commandName")
        return true
    } else {
        // Display an error message if the license key is invalid
        println("Error: Invalid license key for command $commandName")
        return false
    }
}
