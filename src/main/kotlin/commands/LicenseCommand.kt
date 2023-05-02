package commands

import config.Config
import exceptions.CustomException
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.License
import java.io.File
import java.util.*

class LicenseCommand : Command {
    override val name: String = "license"
    override val description: String = "Activate a license key"
    override val usage: String = "license <key>"

    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            throw CustomException("No license key provided")
        }

        val licenseKey = args[0]

        val userConfigFile = File(Config.USER_CONFIG_FILE_PATH)
        if (!userConfigFile.exists()) {
            throw CustomException("User configuration file does not exist")
        }

        val userConfigJson = userConfigFile.readText()
        val userConfig = Json.decodeFromString<UserConfig>(userConfigJson)

        val licenseFile = File(Config.LICENSES_DIR_PATH + "${userConfig.uuid}.json")
        if (!licenseFile.exists()) {
            throw CustomException("No license key found for this user")
        }

        val licenseJson = licenseFile.readText()
        val license = Json.decodeFromString<License>(licenseJson)

        if (license.key != licenseKey) {
            throw CustomException("Invalid license key")
        }

        if (license.used) {
            throw CustomException("License key has already been used")
        }

        license.used = true
        val updatedLicenseJson = Json.encodeToString(license)
        licenseFile.writeText(updatedLicenseJson)

        println("License key activated successfully")
    }

    @Serializable
    data class UserConfig(val uuid: UUID)
}
