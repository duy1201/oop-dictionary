package Trie;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class Trie {
    private TrieNode root = new TrieNode();
    private ArrayList<String> resultWords1 = new ArrayList<>();

    /*
    thêm từ
     */
    public void insert(Word word) {
        TrieNode current = root;

        for (char c : word.getWordTarget().toCharArray()) {
            current = current.getChildren().computeIfAbsent(c, newNode -> new TrieNode());
        }
        current.setContent(word);
        current.setIsWord(true);
    }

    /*
    tìm từ
     */
    public String lookUp(String searchWord) {
        TrieNode current = root;
        for (char ch : searchWord.toLowerCase().toCharArray()) {
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return "Không tìm thấy từ";
            }
            current = node;
        }
        if (current.getIsWord()) {
            return current.getContent().getWordExplain();
        } else {
            return "Không tìm thấy từ";
        }
    }

    /*
    xóa từ
     */
    public void delete(String deleteWord) {
        delete(root, deleteWord.toLowerCase(), 0);
    }

    private boolean delete(TrieNode current, String deleteWord, int index) {
        if (index == deleteWord.length()) {
            if (!current.getIsWord()) {
                return false;
            }
            current.setIsWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = deleteWord.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, deleteWord, index + 1) && !node.getIsWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }

    private void getListWithPrefix(TrieNode current) {
        if (current.getIsWord()) {
            resultWords1.add(current.getContent().getWordTarget());
        }
        SortedSet<Character> children = new TreeSet<>(current.getChildren().keySet());
        for (char c : children) {
            getListWithPrefix(current.getChildren().get(c));
        }
    }

    public ArrayList<String> getListWithPrefix(String targetWord) {
        resultWords1 = new ArrayList<>();
        TrieNode current = root;
        for (char c : targetWord.toLowerCase().toCharArray()) {
            TrieNode node = current.getChildren().get(c);
            if (node == null) {
                return resultWords1;
            }
            current = node;
        }
        getListWithPrefix(current);
        if (resultWords1.size() == 0) {
            return null;
        }
        return resultWords1;
    }
}
