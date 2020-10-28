package gui;

import javax.swing.JFrame;
import spelling.Dictionary;
import spelling.DictionaryLinkedList;

public class  TextEditorMain
{  
   public static void main(String[] args)
   {  
      Dictionary dictionary = new DictionaryLinkedList();
      JFrame frame = new TextEditorFrame(dictionary);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}
