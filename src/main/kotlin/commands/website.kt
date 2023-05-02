import java.awt.Desktop
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class WebsiteCommand(private val userConfig: UserConfig) : Command {
    override val name = "website"
    override val description = "Displays a website in a popup window"
    override val usage = "website <url>"

    override fun execute(args: List<String>): String {
        if (args.isEmpty()) {
            return "Error: Please provide a URL"
        }

        val url = args[0]

        val license = userConfig.getLicense("website")
        if (license == null || license.used || LocalDate.now() > license.expirationDate) {
            return "Error: You do not have a valid license to use this command"
        }

        val html = URL(url).openStream().use { it.bufferedReader().readText() }
        val popup = Popup(html, url)
        popup.show()

        userConfig.useLicense("website")

        return ""
    }
}

class Popup(html: String, url: String) : JFrame(url) {
    init {
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE

        val editorPane = JEditorPane()
        editorPane.contentType = "text/html"
        editorPane.text = html
        editorPane.isEditable = false

        val scrollPane = JScrollPane(editorPane)
        scrollPane.preferredSize = Dimension(800, 600)

        contentPane.add(scrollPane, BorderLayout.CENTER)
        pack()
        isVisible = true
    }
}
