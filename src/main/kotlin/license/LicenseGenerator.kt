import java.security.SecureRandom
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LicenseGenerator {
    private const val LICENSE_KEY_LENGTH = 16
    private val SECURE_RANDOM = SecureRandom()
    private val LICENSE_ISSUE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    /**
     * Generates a new license key for the given user identifier and command.
     *
     * @param userIdentifier The unique identifier for the user.
     * @param command The command for which the license key is being generated.
     * @return The new license key.
     */
    fun generateLicenseKey(userIdentifier: String, command: String): String {
        // Generate a new 16-character random license key
        val licenseKey = generateRandomString(LICENSE_KEY_LENGTH)

        // Generate the current date and time as the issue date
        val issueDate = LocalDateTime.now()

        // Create a new license object with the generated key, user identifier, command, and issue date
        val license = License(licenseKey, userIdentifier, command, issueDate)

        // Write the new license to the user licenses file
        writeLicenseToFile(license)

        return licenseKey
    }

    /**
     * Generates a random string of the given length using the characters A-Z, a-z, and 0-9.
     *
     * @param length The length of the string to generate.
     * @return The random string.
     */
    private fun generateRandomString(length: Int): String {
        val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { chars[SECURE_RANDOM.nextInt(chars.size)] }
            .joinToString("")
    }

    /**
     * Writes a license to the user licenses file.
     *
     * @param license The license to write.
     */
    private fun writeLicenseToFile(license: License) {
        val userLicensesFile = Config.get("USER_LICENSES_FILE")
        if (userLicensesFile == null) {
            throw CustomException("USER_LICENSES_FILE not defined in .env")
        }

        val licenses = readLicensesFromFile()
        licenses.add(license)

        val licensesYaml = buildLicensesYaml(licenses)

        userLicensesFile.writeText(licensesYaml)
    }

    /**
     * Reads the licenses from the user licenses file.
     *
     * @return The list of licenses.
     */
    private fun readLicensesFromFile(): MutableList<License> {
        val userLicensesFile = Config.get("USER
