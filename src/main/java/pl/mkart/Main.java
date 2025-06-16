package pl.mkart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/StartView.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setTitle("M-Kart – System Rezerwacji");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);

        //Dodanie ikony
        Image icon = new Image(getClass().getResourceAsStream("/images/logo.png"));
        primaryStage.getIcons().add(icon);

        primaryStage.show();
    }

    public static void showStartView() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/StartView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Main.class.getResource("/css/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
