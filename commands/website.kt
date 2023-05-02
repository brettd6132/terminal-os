import java.awt.Desktop
import java.net.URI
import java.net.URL
import javax.swing.JEditorPane
import javax.swing.JFrame
import javax.swing.JScrollPane

class WebsiteCommand : Command {
    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("Please specify a website URL.")
            return
        }
        val websiteUrl = args[0]
        try {
            val url = URL(websiteUrl)
            val html = url.readText()
            val editorPane = JEditorPane("text/html", html)
            editorPane.isEditable = false
            val scrollPane = JScrollPane(editorPane)
            val frame = JFrame(url.host)
            frame.contentPane.add(scrollPane)
            frame.setSize(800, 600)
            frame.setLocationRelativeTo(null)
            frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
            frame.isVisible = true
        } catch (e: Exception) {
            println("Failed to load website: ${e.message}")
        }
    }
}
