package Interfaces;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class FirstInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Interface");

        BorderPane borderPane = new BorderPane();

        // Header
        HBox header = new HBox();
        header.setPadding(new Insets(10, 10, 10, 10));
        // header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: #336699;");

        // List of options
        ListView<String> optionsListView = new ListView<>();
        optionsListView.getItems().addAll("Profile", "Schedule", "Statistics", "Sports");
        // Set the size of the ListView
        optionsListView.setPrefWidth(400);
        optionsListView.setPrefHeight(40);

        optionsListView.setOrientation(Orientation.HORIZONTAL); // Set the orientation to horizontal

        optionsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue) {
                    case "Profile":
                        // openInterface(new ProfileInterface(), primaryStage);
                        break;
                    case "Schedule":
                        openInterface(new ScheduleInterface(), primaryStage);
                        break;
                    case "Statistics":
                        // openInterface(new StatisticsInterface(), primaryStage);
                        break;
                    case "Sports":
                        // openInterface(new SportsInterface(), primaryStage);
                        break;
                    default:
                        break;
                }
            }
        });

        // Set the list view as the center of the header
        header.getChildren().add(optionsListView);

        // Set the header as the top of the border pane
        borderPane.setTop(header);

        // Set the image
        Image image = new Image("file:src/images/Gymwallpaper.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(800); // Set the width to fill the space
        imageView.setPreserveRatio(true); // Preserve the aspect ratio

        // Create lorem ipsum text
        Text loremText = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
                + "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        loremText.setStyle("-fx-font-size: 16px;");
        loremText.setTextAlignment(TextAlignment.CENTER);
        loremText.setFill(Color.WHITE);

        // Create a StackPane to hold the image and lorem text
        StackPane centerPane = new StackPane();
        // centerPane.getChildren().addAll(imageView, loremText);
        centerPane.getChildren().addAll(imageView);

        // Set the center pane to the center of the border pane
        borderPane.setCenter(centerPane);

        // Set the border pane to the scene
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openInterface(Application interfaceClass, Stage primaryStage) {
        try {
            interfaceClass.start(new Stage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        primaryStage.close();
    }
}


