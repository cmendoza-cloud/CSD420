// Carmen Mendoza
// CSD420
// Write a JavaFX program that displays four images randomly selected from a deck of 52 cards.

package Module1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CardsDisplay extends Application {

    private static final int CARD_COUNT = 52; // Total number of cards
    private static final int DISPLAY_COUNT = 4; // Number of cards to display
    private static final String CARD_PATH = "C:/Users/cmend/Desktop/CSD420/Module-1/cards/"; // Update to absolute path!

    private final ImageView[] cardViews = new ImageView[DISPLAY_COUNT]; // ImageViews to display cards

    public static void main(String[] args) {
        launch(args); // Launch JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        // HBox to hold card images
        HBox cardBox = new HBox(10); // 10px spacing

        // Initialize ImageViews and add them to the HBox
        for (int i = 0; i < DISPLAY_COUNT; i++) {
            cardViews[i] = new ImageView();
            cardViews[i].setFitHeight(150); // Set the height of card images
            // Set the width of card images
            cardViews[i].setFitWidth(100);
            cardViews[i].setPreserveRatio(true); // Preserve aspect ratio of images
            cardBox.getChildren().add(cardViews[i]); // Add each ImageView to HBox
        }

        // Button to refresh images
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(_ -> updateCards()); // Lambda expression for button action

        // Layout: BorderPane
        BorderPane root = new BorderPane();
        root.setCenter(cardBox); // Center the card HBox
        root.setBottom(refreshButton); // Place Button at the bottom
        BorderPane.setAlignment(refreshButton, javafx.geometry.Pos.CENTER);

        // Initial card display
        updateCards();

        // Scene and Stage setup
        Scene scene = new Scene(root, 450, 250); // Create a Scene
        primaryStage.setTitle("Random Card Display"); // Set the title
        primaryStage.setScene(scene); // Add the Scene to Stage
        primaryStage.show(); // Show the Stage
    }

    private void updateCards() {
        // Use a Set to ensure unique card numbers are picked
        Set<Integer> selectedCards = new HashSet<>();
        Random random = new Random();

        // Randomly select unique card numbers
        while (selectedCards.size() < DISPLAY_COUNT) {
            int cardNumber = random.nextInt(CARD_COUNT) + 1;
            selectedCards.add(cardNumber);
        }

        // Update the card images based on the selected card numbers
        int index = 0;
        for (Integer cardNumber : selectedCards) {
            String cardImagePath = CARD_PATH + "card" + cardNumber + ".png"; // Construct path to image
            Image cardImage = new Image("file:" + cardImagePath); // Load image
            cardViews[index].setImage(cardImage); // Set image in ImageView
            index++;
        }
    }
}
