package pl.mkart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.mkart.controller.StartController;

import java.io.IOException;

@SpringBootApplication
public class Main extends Application {

    private static ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        springContext = SpringApplication.run(Main.class);

        showStartView(stage);
    }

    // Metoda do wyświetlania startowego widoku
    public static void showStartView(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/StartView.fxml"));
        Parent root = loader.load();

        // Pobieramy kontroler i przekazujemy Stage
        StartController controller = loader.getController();
        controller.setStage(stage);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Main.class.getResource("/css/style.css").toExternalForm());

        stage.setTitle("M-Kart – Start");
        stage.setScene(scene);
        stage.show();
    }

    // Metoda, aby zamknąć Spring Boot w momencie zamknięcia aplikacji
    @Override
    public void stop() throws Exception {
        super.stop();
        if (springContext != null) {
            springContext.close();
        }
    }

    public static void showDashboardView(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/DashboardView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Main.class.getResource("/css/style.css").toExternalForm());

        stage.setTitle("M-Kart – Dashboard");
        stage.setScene(scene);
        stage.show();
    }

}
