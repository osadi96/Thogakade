import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/Dashboardform.fxml"))));
        primaryStage.setTitle("MAINMENU");
        Image image = new Image("Img/img1.png");
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }
}