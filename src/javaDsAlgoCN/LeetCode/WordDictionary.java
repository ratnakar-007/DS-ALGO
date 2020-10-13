package javaDsAlgoCN.LeetCode;

class WordDictionaryNode{
    char data;
    boolean isTerminating;
    WordDictionaryNode children[];

    public WordDictionaryNode(char data) {
        this.data = data;
        isTerminating = false;
        children = new WordDictionaryNode[26];
    }
}


public class WordDictionary {

    private WordDictionaryNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new WordDictionaryNode('\0');
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        addWord(root, word);
    }

    private void addWord(WordDictionaryNode root, String word){
        if(word.length() == 0){
            if(root.isTerminating) {
                return;
            }
            else {
                root.isTerminating = true;
                return;
            }
        }
        int childIndex = word.charAt(0) - 'a';
        WordDictionaryNode child = root.children[childIndex];
        if(child == null){
            child = new WordDictionaryNode(word.charAt(0));
            root.children[childIndex] = child;
        }
        addWord(child, word.substring(1));
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word);
    }

    private boolean search(WordDictionaryNode root, String word) {
        if(word.length() == 0){
            return root.isTerminating;
        }
        if (word.charAt(0) == '.') {
            for (WordDictionaryNode child : root.children) {
                if (child != null && search(child, word.substring(1))) {
                    return true;
                }
            }
            return false;
        }
        int childIndex = word.charAt(0) - 'a';
        WordDictionaryNode child = root.children[childIndex];
        if(child == null){
            return false;
        }
        return search(child, word.substring(1));
    }
}
