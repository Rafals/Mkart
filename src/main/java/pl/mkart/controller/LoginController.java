package pl.mkart.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import pl.mkart.MKartApplication.Main;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private static final String LOGIN_URL = "http://localhost:8080/auth/login"; // URL do logowania w backendzie

    @FXML
    private void handleLogin() {
        String email = usernameField.getText();
        String password = passwordField.getText();

        // Sprawdzamy, czy dane są niepuste
        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Wszystkie pola muszą być wypełnione.");
            errorLabel.setVisible(true);
            return;
        }

        // Wywołanie metody do logowania
        loginUser(email, password);
    }

    // Metoda do wysyłania danych logowania do backendu
    private void loginUser(String email, String password) {
        HttpClient client = HttpClient.newHttpClient();

        // Tworzymy dane do wysłania w żądaniu (np. JSON)
        String json = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);

        // Tworzymy zapytanie HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LOGIN_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        // Asynchronicznie wysyłamy zapytanie
        CompletableFuture<Void> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() == 200) {
                        // Jeśli logowanie jest udane, przechodzimy do dashboardu
                        Platform.runLater(() -> {
                            errorLabel.setVisible(false);
                            goToDashboard();
                        });
                    } else {
                        System.out.println("login: " + email);
                        System.out.println("password: " + password);
                        // W przypadku błędu logowania wyświetlamy komunikat
                        Platform.runLater(() -> {
                            errorLabel.setText("Niepoprawne dane logowania.");
                            errorLabel.setVisible(true);
                        });
                    }
                });
    }

    // Funkcja do przejścia do dashboardu po zalogowaniu
    private void goToDashboard() {
        try {
            // Upewnij się, że 'primaryStage' jest już zainicjowane przed wywołaniem showDashboardView()
            if (Main.primaryStage != null) {
                Main.showDashboardView();
                System.out.println("Zalogowano pomyślnie! Przechodzimy do dashboardu.");
            } else {
                System.out.println("primaryStage is null, unable to switch to dashboard.");
            }
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
            Main.showStartView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
