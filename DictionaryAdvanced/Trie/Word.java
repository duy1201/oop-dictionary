package Trie;

public class Word {
    private String wordTarget;
    private String wordExplain;
   // private String wordPronounce;

    Word() {
        wordTarget = "";
        wordExplain = "";
        //wordPronounce = "";
    }

    public Word(String wordTarget, String wordExplain/*, String wordPronounce*/) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        //this.wordPronounce = wordPronounce;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    /*public String getWordPronounce() {
        return wordPronounce;
    }

    public void setWordPronounce(String wordPronounce) {
        this.wordPronounce = wordPronounce;
    }*/
}
