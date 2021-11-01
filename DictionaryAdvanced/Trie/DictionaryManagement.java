package Trie;

import Trie.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private Trie dictionary = new Trie();

    public void getFromTxt() {
        try {
            File myObj = new File("src/Trie/dictionaries.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split("#");
                String wordTaget = data[0].toLowerCase().trim();
                String wordExplain = "";
                for (int i = 1; i < data.length; i++) {
                    wordExplain += data[i] + "\n";
                }
                addWord(wordTaget, wordExplain);
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWord(String wordTaget, String wordExplain) {
        dictionary.insert(new Word(wordTaget, wordExplain));
    }

    public void deleteWord(String deleteWord) {
        dictionary.delete(deleteWord);
    }

    public String lookUp(String searchWord) {
        return dictionary.lookUp(searchWord);
    }

    public ArrayList<String> getSuggestList(String searchWord) {
        return dictionary.getListWithPrefix(searchWord);
    }

}

