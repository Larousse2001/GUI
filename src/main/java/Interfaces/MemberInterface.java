package Interfaces;

import Models.Member;
import Controllers.MemberController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class MemberInterface extends Application {

    private MemberController memberController;

    private ObservableList<Member> memberList;

    private TextField userNameField;
    private TextField emailField;
    private TextField membershipField;
    private TextField ageField;
    private TextField heightField;
    private TextField weightField;
    private DatePicker schedulePicker;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        memberController = new MemberController();

        primaryStage.setTitle("Member Management");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 0);

        userNameField = new TextField();
        userNameField.setPromptText("Enter username");
        GridPane.setConstraints(userNameField, 1, 0);

        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 1);

        emailField = new TextField();
        emailField.setPromptText("Enter email");
        GridPane.setConstraints(emailField, 1, 1);

        Label membershipLabel = new Label("Membership:");
        GridPane.setConstraints(membershipLabel, 0, 2);

        membershipField = new TextField();
        membershipField.setPromptText("Enter membership");
        GridPane.setConstraints(membershipField, 1, 2);

        Label ageLabel = new Label("Age:");
        GridPane.setConstraints(ageLabel, 0, 3);

        ageField = new TextField();
        ageField.setPromptText("Enter age");
        GridPane.setConstraints(ageField, 1, 3);

        Label heightLabel = new Label("Height:");
        GridPane.setConstraints(heightLabel, 0, 4);

        heightField = new TextField();
        heightField.setPromptText("Enter height");
        GridPane.setConstraints(heightField, 1, 4);

        Label weightLabel = new Label("Weight:");
        GridPane.setConstraints(weightLabel, 0, 5);

        weightField = new TextField();
        weightField.setPromptText("Enter weight");
        GridPane.setConstraints(weightField, 1, 5);

        Label scheduleLabel = new Label("Schedule:");
        GridPane.setConstraints(scheduleLabel, 0, 6);

        schedulePicker = new DatePicker();
        GridPane.setConstraints(schedulePicker, 1, 6);
        GridPane.setConstraints(schedulePicker, 1, 6);

        Button addButton = new Button("Add");
        GridPane.setConstraints(addButton, 1, 7);
        addButton.setOnAction(e -> {
            try {
                addMember();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        TableView<Member> table = new TableView<>();
        memberList = FXCollections.observableArrayList();
        table.setItems(memberList);

        TableColumn<Member, String> nameColumn = new TableColumn<>("Username");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().userNameDBProperty());
        table.getColumns().add(nameColumn);

        TableColumn<Member, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().userEmailDBProperty());
        table.getColumns().add(emailColumn);

        /* Add more columns as needed */

        GridPane.setConstraints(table, 0, 8, 2, 1);

        grid.getChildren().addAll(nameLabel, userNameField, emailLabel, emailField,
                membershipLabel, membershipField, ageLabel, ageField,
                heightLabel, heightField, weightLabel, weightField,
                scheduleLabel, schedulePicker, addButton, table);

        // Scene Setup
        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initial load of members
        refreshMembers();
    }

    private void addMember() throws SQLException {
        String username = userNameField.getText();
        String email = emailField.getText();
        String membership = membershipField.getText();
        int age = Integer.parseInt(ageField.getText());
        double height = Double.parseDouble(heightField.getText());
        double weight = Double.parseDouble(weightField.getText());
        // Convert LocalDate to java.sql.Date
        java.sql.Date schedule = java.sql.Date.valueOf(schedulePicker.getValue());

        Member newMember = new Member(username, email, membership, age, height, weight, schedule, null);

        memberController.addMember(newMember);
        memberList.add(newMember);
        clearFields();
    }

    private void refreshMembers() throws SQLException {
        List<Member> members = memberController.getAllMembers();
        if (members != null) {
            memberList.clear();
            memberList.addAll(members);
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", "No members found", "There are no members available.");
        }
    }

    private void clearFields() {
        userNameField.clear();
        emailField.clear();
        membershipField.clear();
        ageField.clear();
        heightField.clear();
        weightField.clear();
        schedulePicker.getEditor().clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
