package Interfaces;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SportsInterface extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sports Interface");

        // Create a GridPane to hold sports information
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(20);
        gridPane.setVgap(10);

        // Sample sports data
        String[] sports = {"Boxing", "Swimming", "Cardio", "Dance", "Musculation"};
        String[] imagePaths = {"boxing.jpg", "swimming.jpg", "cardio.jpeg", "dance.jpeg", "musculation.jpg"};

        // Populate the GridPane with sports information and images
        for (int i = 0; i < sports.length; i++) {
            ImageView imageView = new ImageView(new Image("file:src/images/" + imagePaths[i]));
            imageView.setFitWidth(200); // Set image width
            imageView.setFitHeight(150); // Set image height

            Text sportText = new Text(sports[i]);
            sportText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            VBox vbox = new VBox(imageView, sportText);
            vbox.setAlignment(Pos.CENTER);
            gridPane.add(vbox, 0, i);
        }

        // Create a ScrollPane and add the GridPane to it
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Create the Scene
        Scene scene = new Scene(scrollPane, 600, 400);

        // Set the Scene and show the Stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
