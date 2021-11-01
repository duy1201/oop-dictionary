package UI;

import UI.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class OnlineDict extends MainController {
    @FXML
    private Button swapButton;

    @FXML
    private Button translateButton;

    @FXML
    private TextArea searchTextField;

    @FXML
    private TextArea resultTextArea;

    @FXML
    private Label searchLabel;

    @FXML
    private Label resultLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       resultTextArea.setEditable(false);
       resultTextArea.setWrapText(true);
       searchTextField.setWrapText(true);
    }

    public void swapLanguage(ActionEvent e) throws IOException {
        String tmp = searchLabel.getText();
        searchLabel.setText(resultLabel.getText());
        resultLabel.setText(tmp);
       /* if (!resultTextArea.getText().equals("")) {
            String searchWord = resultTextArea.getText();
            searchTextField.setText(searchWord);
            String resultWord;
            if (searchLabel.getText().equals("Tiếng Việt")) {
                resultWord = translate("vi", "en", searchWord);
            } else {
                resultWord = translate("en", "vi", searchWord);
            }
            resultTextArea.setText(resultWord);
        }*/
    }

    public void translate(ActionEvent e) throws IOException {
        String searchWord = searchTextField.getText();
        String resultWord;
        if (searchWord != null) {
            if (searchLabel.getText().equals("Tiếng Việt")) {
                resultWord = translate("vi", "en", searchWord);
            } else {
                resultWord = translate("en", "vi", searchWord);
            }
            resultTextArea.setText(resultWord);
        }
    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbwHsg9Ywpg25EwswiLFGGSCVKaN3eNr8QxsGrdDe9ofcfZIZds/exec" +
                "?q=" + URLEncoder.encode(text, StandardCharsets.UTF_8) +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
