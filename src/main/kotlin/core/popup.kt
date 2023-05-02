import javax.swing.JOptionPane

class CustomException(message: String) : Exception(message) {
    init {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE)
    }
}
