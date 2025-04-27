import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class FourCircles extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create four circles
        Circle circle1 = new Circle(50);
        circle1.setId("redcircle");

        Circle circle2 = new Circle(50);
        circle2.setId("greencircle");

        Circle circle3 = new Circle(50);
        circle3.getStyleClass().add("plaincircle");

        Circle circle4 = new Circle(50);
        circle4.getStyleClass().addAll("plaincircle", "circleborder");

        // Add to layout
        HBox hbox = new HBox(20, circle1, circle2, circle3, circle4);
        hbox.getStyleClass().add("border");
        hbox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Load the CSS
        Scene scene = new Scene(hbox);
        scene.getStylesheets().add("mystyle.css");

        // Set the stage
        primaryStage.setTitle("Four Circles with CSS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

