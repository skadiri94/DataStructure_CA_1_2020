/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spelling;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Use this for profiling spell checking
public class SpellCheckerTeeSet {
    private Dictionary dictionary;

    public SpellCheckerTeeSet(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    //This is the method you wish to profile
    private List<String> doSpellCheck(List<String> words, Dictionary dictionary) {
        List<String> list = new ArrayList();//list to store mispelt words
        for (String word : words) {
            if (!dictionary.isWord(word)) {
                list.add(word);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Hit return to continue");

        //Test with LinkedList dictionary - do separate runs for TreeSet and HashSet dictionaries
        Dictionary dictionary = new DictionaryLinkedList();

        SpellCheckerTeeSet checker = new SpellCheckerTeeSet(dictionary);

        List<String> words = readWords("words.txt");

        checker.doSpellCheck(words, dictionary);
    }

    public static List<String> readWords(String filename) {
        List<String> words = new ArrayList();
        try {
            Scanner in = new Scanner(new File(filename));
            // Use any characters other than a-z or A-Z as delimiters
            in.useDelimiter("[^a-zA-Z]+");
            while (in.hasNext()) {
                String s = in.next();
                words.add(in.next().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        }
        return words;
    }
}