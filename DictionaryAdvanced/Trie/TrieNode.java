package Trie;

import java.util.HashMap;

public class TrieNode {
    private HashMap<Character, TrieNode> children = new HashMap<>();
    private Word content;
    private boolean isWord;

    HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    Word getContent() {
        return content;
    }

    void setContent(Word content) {
        this.content = content;
    }

    boolean getIsWord() {
        return isWord;
    }

    void setIsWord(boolean isWord) {
        this.isWord = isWord;
    }
}
