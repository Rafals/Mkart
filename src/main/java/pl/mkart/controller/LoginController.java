package pl.mkart.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.mkart.Main;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin() {

        String username = usernameField.getText();
        String password = passwordField.getText();

        // Tymczasowa weryfikacja
        if ("admin".equals(username) && "1234".equals(password)) {
            System.out.println("Zalogowano pomyślnie!");
            errorLabel.setVisible(false);
            // TODO: przejście do dashboardu
        } else {
            errorLabel.setText("Nieprawidłowa nazwa użytkownika lub hasło.");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void closeApp() {
        Platform.exit();
    }

    @FXML
    private void goBackApp() {
        try {
            Main.showStartView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
