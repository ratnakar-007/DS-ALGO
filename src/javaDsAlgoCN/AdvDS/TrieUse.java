package javaDsAlgoCN.AdvDS;

import java.util.ArrayList;

public class TrieUse {
    public static void main(String[] args) {
        Trie trie = new Trie();

        /*ArrayList<String> al = new ArrayList<>();
        al.add("apple");
        al.add("eye");
        al.add("of");
        al.add("the");
        al.add("tiger");
        al.add("ti");
        al.add("tim");

        trie.autoComplete(al, "ti");*/

        trie.add("abc");
        trie.add("add");
        trie.add("aer");
        trie.add("bad");
        trie.add("dad");

        System.out.println(trie.searchDot("abc"));
        System.out.println(trie.searchDot("..c"));
        System.out.println(trie.searchDot("da."));
        System.out.println(trie.searchDot("ba.."));
        System.out.println(trie.searchDot("b.."));

    }
}
