package UI;

import Trie.DictionaryManagement;
import Trie.VoiceImplement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LookUpController extends MainController {

    @FXML
    private TextField myTextField;

    @FXML
    private Button searchButton;

    @FXML
    private TextArea meaning;

    @FXML
    private ListView<String> suggestWordList;

    @FXML
    private Label searchWordLabel;

    @FXML
    private Button speech;

    @FXML
    private Button onlineButton;

    @FXML
    private Label correctWordLabel;

    @FXML
    private MenuButton myMenuButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        meaning.setEditable(false);
        correctWordLabel.setText("");
        //speech.setDisable(true);
        speech.setVisible(false);
    }

    public void setData() {
        DictionaryManagement x = new DictionaryManagement();
        x.getFromTxt();
        setDictionaryManager(x);
        //System.out.println("co chay");
    }

    public void searchBySearchButton(ActionEvent event) {
        String searchWord = myTextField.getText();
        search(searchWord);
    }

    public void searchByEnter(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            String searchWord = myTextField.getText();
            search(searchWord);
            //myTextField.setText(searchWord.trim());
        }
    }

    public void search(String searchWord) {
        String result = getDictionaryManager().lookUp(searchWord.trim());
        if (!result.equals("Không tìm thấy từ")) {
            correctWordLabel.setText(searchWord.trim());
            //speech.setDisable(false);
            speech.setVisible(true);
        } else {
            //speech.setDisable(true);
            speech.setVisible(false);
            correctWordLabel.setText("");
        }
        searchWordLabel.setText("");
        meaning.setText(result);
    }

    public void createSuggestList(KeyEvent event) {
        String searchWord = myTextField.getText().trim();
        suggest(searchWord);
        if (searchWord.equals("") || event.getCode() == KeyCode.ENTER) {
            searchWordLabel.setText("");
        }
    }

    public void searchFromSuggestList(MouseEvent event) {
        if (suggestWordList.getSelectionModel().getSelectedItem() != null) {
            String correctWord = suggestWordList.getSelectionModel().getSelectedItem();
            search(correctWord);
        }
    }

    public void speechWord(ActionEvent event) {
        VoiceImplement voice = new VoiceImplement();
        String correctWord = correctWordLabel.getText();
        if (!correctWord.equals("")) {
            voice.textToSpeech(correctWord);
        }
    }

    public void suggest(String searchWord) {
        ArrayList<String> resultList = getDictionaryManager().getSuggestList(searchWord);
        if (resultList.isEmpty()) {
            searchWordLabel.setText("Không có từ thích hợp! Vui lòng kiểm tra lại!");
        } else {
            searchWordLabel.setText("Đang tìm kiếm...");
        }
        ArrayList<String> wordsFound = new ArrayList<>();
        int size = Math.min(resultList.size(), 100);
        for (int i = 0; i < size; i++) {
            wordsFound.add(resultList.get(i));
        }
        ObservableList<String> items = FXCollections.observableArrayList(wordsFound);
        suggestWordList.setItems(items);
    }
}
