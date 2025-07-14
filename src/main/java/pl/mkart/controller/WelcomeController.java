package pl.mkart.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import pl.mkart.Main;

import java.io.IOException;

public class WelcomeController {

    private Stage stage;

    // Ustawiany z Main.java po załadowaniu FXML
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void closeApp() {
        Platform.exit();
    }

    @FXML
    private void goBackApp() {
        try {
            if (stage != null) {
                Main.showStartView(stage); // ← przekazujemy dalej ten sam stage
            } else {
                System.out.println("Stage is null!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
