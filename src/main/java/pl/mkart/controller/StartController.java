package pl.mkart.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.mkart.Main;

import java.io.IOException;

public class StartController {

    private Stage stage;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Tutaj Twoja logika uwierzytelniania
        if ("admin".equals(username) && "admin".equals(password)) {
            try {
                Main.showDashboardView(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Nieprawidłowe dane logowania.");
        }
    }

    @FXML
    private void handleExit() {
        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    private void closeApp() {
        if (stage != null) {
            stage.close();  // zamyka okno, ale nie zabija JVM
        } else {
            System.exit(0); // fallback
        }
    }

    @FXML
    private void handleGoToSystem() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/LoginView.fxml"));
            Parent root = loader.load();

            // PRZEKAZUJEMY STAGE
            LoginController loginController = loader.getController();
            loginController.setStage(stage);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(Main.class.getResource("/css/style.css").toExternalForm());

            stage.setScene(scene);
            stage.setTitle("M-Kart – Logowanie");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

