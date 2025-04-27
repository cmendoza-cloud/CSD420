//Carmen Mendoza 
//CSD420 
//Module 8 
//In this class, you are to use three threads to output three types of characters to a text area for display.

import javax.swing.*;
import java.util.Random;

public class CarmenThreeThreads extends JFrame {

    // Text area where all characters will be shown
    private JTextArea textArea;

    public CarmenThreeThreads() {
        // Set up the window
        setTitle("CarmenThreeThreads");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a text area to display output
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(new JScrollPane(textArea));

        // Start all 3 threads
        startThreads();
    }

    // This method starts all three threads
    private void startThreads() {
        // Thread for random letters
        Thread lettersThread = new Thread(() -> {
            printRandomChars("letters");
        });

        // Thread for random digits
        Thread digitsThread = new Thread(() -> {
            printRandomChars("digits");
        });

        // Thread for random symbols
        Thread symbolsThread = new Thread(() -> {
            printRandomChars("symbols");
        });

        // Start them!
        lettersThread.start();
        digitsThread.start();
        symbolsThread.start();
    }

    // This method prints 10,000 characters of a certain type
    private void printRandomChars(String type) {
        Random random = new Random();
        String characters = "";

        // Choose the right characters based on the type
        if (type.equals("letters")) {
            characters = "abcdefghijklmnopqrstuvwxyz";
        } else if (type.equals("digits")) {
            characters = "0123456789";
        } else if (type.equals("symbols")) {
            characters = "!@#$%&*";
        }

        // Loop to get 10,000 random characters
        for (int i = 0; i < 10000; i++) {
            char c = characters.charAt(random.nextInt(characters.length()));

            // Make sure we update the text area from the GUI thread
            SwingUtilities.invokeLater(() -> {
                textArea.append(String.valueOf(c));
            });
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Run the program on the GUI thread
        SwingUtilities.invokeLater(() -> {
            CarmenThreeThreads app = new CarmenThreeThreads();
            app.setVisible(true);
        });
    }
}

