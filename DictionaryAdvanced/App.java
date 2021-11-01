package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("lookup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            LookUpController controller = loader.getController();
            controller.setData();
            primaryStage.setTitle("Dictionary");
            Image icon = new Image("img/DictIcon.png");
            primaryStage.getIcons().add(icon);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}