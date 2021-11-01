package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddController extends MainController {

    @FXML
    private TextField searchWordField;

    @FXML
    private TextField pronoucedField;

    @FXML
    private TextField partOfSpeechField;

    @FXML
    private TextArea meaningField;

    @FXML
    private Button addEditButton;

    @FXML
    private Button removeButton;

    @FXML
    private Label myLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        meaningField.setWrapText(true);
        myLabel.setText("");
    }

    public void clearAll() {
        searchWordField.setText("");
        myLabel.setText("");
        meaningField.setText("");
        partOfSpeechField.setText("");
        pronoucedField.setText("");
    }

    public void addOrEdit(ActionEvent event) {
        String searchWord = searchWordField.getText();
        String meaning = meaningField.getText();
        String partOfSpeech = partOfSpeechField.getText();
        String pronounced = pronoucedField.getText();
        if (searchWord != null && !searchWord.equals("") && meaning!=null && !meaning.equals("") ) {
            searchWord = searchWord.toLowerCase().trim();
            String wordExplain ="";
            if (pronounced != null && !pronounced.equals(""))  {
                wordExplain += "/" + pronounced.toLowerCase().trim() + "/" + "\n";
            }
            if (partOfSpeech != null && !partOfSpeech.equals(""))  {
                wordExplain += "   " + partOfSpeech.toLowerCase().trim()  + "\n";
            }
            wordExplain += "      - " + meaning;
            if (getDictionaryManager().lookUp(searchWord).equals("Không tìm thấy từ")) {
                getDictionaryManager().addWord(searchWord, wordExplain);
                printSuccessAddAlert(searchWord);
                clearAll();
            }
            else {
                getDictionaryManager().deleteWord(searchWord);
                getDictionaryManager().addWord(searchWord, wordExplain);
                printSuccessEditAlert(searchWord);
                clearAll();
            }
        } else {
            printFailedAddEditAlert();
        }
    }

    public void removeWord(ActionEvent event) {
        String searchWord = searchWordField.getText();
        if (searchWord != null && !searchWord.trim().equals("")) {
            if (!getDictionaryManager().lookUp(searchWord).equals("Không tìm thấy từ")) {
                getDictionaryManager().deleteWord(searchWord.toLowerCase().trim());
                printSuccessRemoveAlert(searchWord);
                clearAll();
            } else {
                //System.out.println("Lỗi mnr");
                printFailedRemoveAlert();
            }
        } else {
            printFailedRemoveAlert();
            //System.out.println("Lỗi mnr");
        }
    }

    public void printFailedRemoveAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Lỗi");
        alert.setContentText("Không tìm thấy từ. Vui lòng kiểm tra lại");
        alert.show();
    }

    public void printFailedAddEditAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Lỗi");
        alert.setContentText("Thiếu từ hoặc giải thích. Vui lòng kiểm tra lại");
        alert.show();
    }

    public void printSuccessRemoveAlert(String searchWord) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Xóa từ thành công");
        alert.setContentText("Từ " + searchWord.toUpperCase() + " đã bị xóa khỏi từ điển của bạn");
        alert.show();
    }

    public void printSuccessEditAlert(String searchWord) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Sửa từ thành công");
        alert.setContentText("Từ " + searchWord.toUpperCase() + " đã được sửa lại nghĩa");
        alert.show();
    }

    public void printSuccessAddAlert(String searchWord) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Thêm từ thành công");
        alert.setContentText("Từ " + searchWord.toUpperCase() + " đã được thêm vào từ điển của bạn");
        alert.show();
    }

    public void editLabel(KeyEvent event) {
        String searchWord = searchWordField.getText().toLowerCase().trim();
        if (!searchWord.equals("")) {
            if (!getDictionaryManager().lookUp(searchWord).equals( "Không tìm thấy từ")) {
                myLabel.setText("Từ này đã có trong từ điển của bạn");
            }
            else {
                myLabel.setText("Từ này chưa có trong từ điển của bạn");
            }
        }
        else {
            myLabel.setText("");
        }
    }
}
