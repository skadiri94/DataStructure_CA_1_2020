/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spelling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class DictionaryLinkedList implements Dictionary {
    private LinkedList<String> dictionary;
    
    public DictionaryLinkedList(){
        load("words_alpha.txt");
    }

    @Override
    public void load(String fileName) {
        dictionary = new LinkedList();
        try 
        {
            // Prepare to read from the file, using a Scanner object
            File file = new File(fileName);
            Scanner in = new Scanner(file);

            // Read each word until end of file is reached
            while (in.hasNextLine())
            {
                // Read one word from the file
                String word = in.nextLine();
                dictionary.add(word);
            }
        }

        catch (FileNotFoundException e)
        {
            System.out.println("File not found");

        }   
    }

    @Override
    public boolean isWord(String s) {
        return dictionary.contains(s);
    }
    
    @Override
    public boolean addWord(String word) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
