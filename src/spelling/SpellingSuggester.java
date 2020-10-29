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
        List<String> list = new ArrayList();

        int len = word.length() - 1;
        //removing first char
        if (dictionary.isWord(word.substring(1))) {
            list.add(word.substring(1));
        }
        for (int i = 1; i < len; i++) {
            //removing char from between first and last
            String working = word.substring(0, i);
            working = working.concat(word.substring((i + 1), word.length()));
            if (dictionary.isWord(working)) {
                list.add(working);
            }
        }
        if (dictionary.isWord(word.substring(0, len))) {
            list.add(word.substring(0, len));
        }
        return list;

     //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
