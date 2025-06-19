package pl.mkart.MKartApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Main extends Application {

    private static Stage primaryStage;
    private static ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        // Uruchamiamy Spring Boot w osobnym wątku, aby JavaFX działał równolegle
        springContext = SpringApplication.run(Main.class, args);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        // Inicjalizacja JavaFX
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/StartView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setTitle("M-Kart – System Rezerwacji");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);

        // Dodanie ikony
        Image icon = new Image(getClass().getResourceAsStream("/images/logo.png"));
        primaryStage.getIcons().add(icon);

        primaryStage.show();
    }

    // Metoda do wyświetlania startowego widoku
    public static void showStartView() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/StartView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Main.class.getResource("/css/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Metoda, aby zamknąć Spring Boot w momencie zamknięcia aplikacji
    @Override
    public void stop() throws Exception {
        super.stop();
        if (springContext != null) {
            springContext.close();
        }
    }
}
