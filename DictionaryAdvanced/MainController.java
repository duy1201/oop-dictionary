package UI;

import Trie.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    private DictionaryManagement dictionaryManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public DictionaryManagement getDictionaryManager() {
        return dictionaryManager;
    }

    public void setDictionaryManager(DictionaryManagement dicmana) {
        dictionaryManager = dicmana;
    }

    public void switchToEdit(ActionEvent e) throws IOException {
        try {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add.fxml"));
            Parent viewParent = loader.load();
            Scene scene = new Scene(viewParent);
            AddController controller = loader.getController();
            controller.setDictionaryManager(dictionaryManager);
//            scene.getStylesheets().add
//                    ("/UI/navigation-button.css");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
        }
    }

    public void switchToOnlineDict(ActionEvent e) throws IOException {
        try {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("onlinedict.fxml"));
            Parent viewParent = loader.load();
            Scene scene = new Scene(viewParent);
            OnlineDict controller = loader.getController();
            controller.setDictionaryManager(dictionaryManager);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
        }
    }

    public void switchToLookUp(ActionEvent e) {
        try {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("lookup.fxml"));
            Parent viewParent = loader.load();
            Scene scene = new Scene(viewParent);
            LookUpController controller = loader.getController();
            controller.setDictionaryManager(dictionaryManager);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
        }
    }

}
