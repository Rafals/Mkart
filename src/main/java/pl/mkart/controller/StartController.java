package pl.mkart.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML
    private Button goToSystemButton;

    @FXML
    private void handleGoToSystem() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
            Parent loginRoot = loader.load();
            Stage stage = (Stage)  goToSystemButton.getScene().getWindow();
            stage.setScene(new Scene(loginRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeApp() {
        Platform.exit();
    }
}
