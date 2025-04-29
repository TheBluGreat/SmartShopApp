// App.java
// Entry point of the program that launches the GUI
import javax.swing.SwingUtilities; // Swing utility to launch GUI correctly

public class App {
    public static void main(String[] args) {
        // Start the GUI safely in the Event Dispatch Thread (EDT)
        // This prevents thread-related issues in GUI rendering
        SwingUtilities.invokeLater(() -> {
            // Create an instance of the GUI window (UserInterface) and make it visible
            new UserInterface().setVisible(true);
        });
    }
}