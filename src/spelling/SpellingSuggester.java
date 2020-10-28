package spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SpellingSuggester {

    private Dictionary dictionary;

    public SpellingSuggester(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> getSuggestions(String word) {
        List<String> list = new ArrayList();
        list.add("Any value"); // for testing

        //call the replacements, deletions etc methods and add results to list

        return list;
    }

    private List<String> replacements(String word) {
        // All replacements of a letter in word

        List<String> list = new ArrayList();

        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (word.charAt(i) != c) {
                    String s = word.substring(0, i) + String.valueOf(c) + word.substring(i + 1);
                    if (dictionary.isWord(s)) {
                        list.add(s);
                    }
                }
            }
        }
        return list;
    }

    private List<String> deletions(String word) {
        // All deletions of a letter in word

       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<String> insertions(String word) {
        // All insertions of a letter in word

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<String> transpositions(String word){
        // All transpositions of two letters in word i.e. swap adjacent letters

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
