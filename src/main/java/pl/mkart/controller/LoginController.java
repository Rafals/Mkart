package pl.mkart.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.mkart.Main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private static final String LOGIN_URL = "http://localhost:8000/auth/login"; // URL do logowania w backendzie

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Sprawdzamy, czy dane są niepuste
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Wszystkie pola muszą być wypełnione.");
            errorLabel.setVisible(true);
            return;
        }

        // Wywołanie metody do logowania
        loginUser(username, password);
    }

    // Metoda do wysyłania danych logowania do backendu
    private void loginUser(String username, String password) {
        HttpClient client = HttpClient.newHttpClient();

        // Tworzymy dane do wysłania w żądaniu (np. JSON)
        String json = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);
        System.out.println(json);

        String basicAuth = "Basic " + Base64.getEncoder()
                .encodeToString((username + ":" + password).getBytes());

        // Tworzymy zapytanie HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LOGIN_URL))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", basicAuth)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        // Asynchronicznie wysyłamy zapytanie
        CompletableFuture<Void> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() == 200) {
                        // Jeśli logowanie jest udane, przechodzimy do dashboardu
                        Platform.runLater(() -> {
                            errorLabel.setVisible(false);
                            handleLogout();
                        });
                    } else {
                        System.out.println(response.statusCode());
                        System.out.println("login: " + username);
                        System.out.println("password: " + password);
                        // W przypadku błędu logowania wyświetlamy komunikat
                        Platform.runLater(() -> {
                            errorLabel.setText("Niepoprawne dane logowania.");
                            errorLabel.setVisible(true);
                        });
                    }
                });
    }

    //                                              FXML
    private Stage stage;

    // Ustawiany z Main.java po załadowaniu FXML
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleLogout() {
        try{
            if (stage != null) {
                Main.showWelcomeView(stage);
            } else System.out.println("stage is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
