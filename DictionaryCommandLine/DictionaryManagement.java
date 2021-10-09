import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DictionaryManagement {
    private Map<String, String> dictionaries = new TreeMap<String, String>();

    public void saveToTxt() {
        try {
            FileWriter file = new FileWriter("dictionary.txt");
            for (Map.Entry<String, String> entry : dictionaries.entrySet()) {
                file.write(entry.getKey() + ";" + entry.getValue() + "\n");
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFromTxt() {
        try {
            File myObj = new File("dictionary.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] dictionary = myReader.nextLine().split(";");
                addWord(dictionary[0], dictionary[1]);
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String addWord(String word, String meaning) {
        return this.dictionaries.put(word, meaning);
    }

    public String deleteWord(String word) {
        return this.dictionaries.remove(word);
    }

    public String lookUp(String word) {
        String meaning = this.dictionaries.get(word);
        return meaning;
    }

    public void showAllWords() {
        System.out.printf("%-15s %-10s\n","WORD","MEANING");
        for (String i : dictionaries.keySet()) {
            System.out.printf("%-15s %-10s \n", i, dictionaries.get(i));
        }
    }
}
