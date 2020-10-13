package javaDsAlgoCN.AdvDS;

import java.util.ArrayList;

class TrieNode{
    char data;
    boolean isTerminating;
    TrieNode children[];
    int childCount;

    public TrieNode(char data) {
        this.data = data;
        isTerminating = false;
        children = new TrieNode[26];
        childCount = 0;
    }
}


public class Trie {

    private TrieNode root;
    private int numWords;

    public Trie() {
        root = new TrieNode('\0');
        numWords = 0;
    }

    private boolean searchDot(TrieNode root, String word) {
        if(word.length() == 0){
            return root.isTerminating;
        }
        if (word.charAt(0) == '.') {
            for (TrieNode child : root.children) {
                if (child != null && searchDot(child, word.substring(1))) {
                    return true;
                }
            }
            return false;
        }
        int childIndex = word.charAt(0) - 'a';
        TrieNode child = root.children[childIndex];
        if(child == null){
            return false;
        }
        return searchDot(child, word.substring(1));
    }

    public boolean searchDot(String word) {
        return searchDot(root, word);
    }

    public boolean search(String word){
        return search(root, word);
    }

    private boolean search(TrieNode root, String word) {
        if(word.length() == 0){
            return root.isTerminating;
        }
        int childIndex = word.charAt(0) - 'a';
        TrieNode child = root.children[childIndex];
        if(child == null){
            return false;
        }
        return search(child, word.substring(1));
    }

    public void remove(String word){
        if(remove(root, word)) {
            numWords--;
        }
    }

    private boolean remove(TrieNode root, String word) {
        if(word.length() == 0){
            if(root.isTerminating) {
                root.isTerminating = false;
                return true;
            }
            else {
                return false;
            }
        }
        int childIndex = word.charAt(0) - 'a';
        TrieNode child = root.children[childIndex];
        if(child == null){
            return false;
        }
        boolean ans = remove(child, word.substring(1));
        // We can remove child node only if it is non terminating and its number of children are 0

        if(!child.isTerminating && child.childCount == 0){
            root.children[childIndex] = null;
            child = null;
            root.childCount--;
        }
        return ans;
    }

    private boolean add(TrieNode root, String word){
        if(word.length() == 0){
            if(root.isTerminating) {
                return false;
            }
            else {
                root.isTerminating = true;
                return true;
            }
        }
        int childIndex = word.charAt(0) - 'a';
        TrieNode child = root.children[childIndex];
        if(child == null){
            child = new TrieNode(word.charAt(0));
            root.children[childIndex] = child;
            root.childCount++;
        }
        return add(child, word.substring(1));
    }

    public void add(String word){
        if(add(root, word)) {
            numWords++;
        }
    }

    public int countWords() {
        return numWords;
    }

    private void autoCompleteRec(TrieNode root, String prefix) {
        if (root.isTerminating) {
            System.out.println(prefix);
        }

        if (root.childCount == 0) {
            return;
        }
        for (int i = 0 ; i < 26 ; i++) {
            TrieNode child = root.children[i];
            if (child != null) {
                autoCompleteRec(child, prefix + child.data);
               // prefix = prefix.substring(0, prefix.length()-1);
            }
        }
    }

    private TrieNode isPrefix(TrieNode root, String word) {
        if(word.length() == 0){
            return root;
        }
        int childIndex = word.charAt(0) - 'a';
        TrieNode child = root.children[childIndex];
        if(child == null){
            return null;
        }
        return isPrefix(child, word.substring(1));
    }

    public void autoComplete(ArrayList<String> input, String word) {

        for (String s : input) {
            add(root, s);
        }

        TrieNode lastNodeWord = isPrefix(root, word);

        if (lastNodeWord == null) {
            return;
        }

        String prefix = word;
        autoCompleteRec(lastNodeWord, prefix);
    }

}
