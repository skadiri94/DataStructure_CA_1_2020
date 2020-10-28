package gui;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import spelling.Dictionary;
import spelling.SpellingSuggester;

public class TextEditorFrame extends JFrame {

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 250;

    private static final int TEXT_AREA_ROWS = 10;
    private static final int TEXT_AREA_COLUMNS = 30;

    private static final int NUM_SUGGESTIONS = 20;

    private JTextArea textArea;

    private int start;
    private int end;

    private int wordLength;

    private Dictionary dictionary;

    public TextEditorFrame(Dictionary dictionary) {

        this.dictionary = dictionary;

        textArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
        textArea.setText("testing" + "\n");
        textArea.setEditable(true);
        textArea.addMouseListener(new MyMouseListener());
        
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);
        add(panel);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private class MyMouseListener implements MouseListener {

        private String word;
        private boolean isValidLeftClick;

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getButton() == MouseEvent.BUTTON1) {
                isValidLeftClick = false;
                int position = textArea.getCaretPosition();
                //check caret within the text
                if (position < textArea.getText().length()) {
                    char ch = textArea.getText().charAt(position);
                    if (!Character.isWhitespace(ch)) {
                        word = getWordAtPosition(position);
                        isValidLeftClick = true;
                    }
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if (isValidLeftClick) {
                    if (!dictionary.isWord(word)) {
                        showSuggestions(e, word, dictionary);
                    } else {
                        displayValidWordMessage();

                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private String getWordAtPosition(int position) {
        String s = textArea.getText().substring(0, position);

        //get first whitespace before caret
        int i = s.length() - 1;
        boolean spaceFound = false;
        while (i >= 0 && !spaceFound) {
            if (Character.isWhitespace(s.charAt(i))) {
                spaceFound = true;
            } else {
                i--;
            }
        }

        if (i < 0) {
            start = 0;
        } else {
            start = i + 1;  
        }
        String beforeCaretString = s.substring(i + 1, s.length());

        //get first whitespace after caret
        int length = textArea.getText().length();

        s = textArea.getText().substring(position, length);
        int l = s.length();
        i = 0;
        spaceFound = false;

        while (i < l && !spaceFound) {
            if (Character.isWhitespace(s.charAt(i))) {
                spaceFound = true;
            } else {
                i++;
            }
        }

        end = i - 1 + position;

        String afterCaretString;
        if (i > l) {
            afterCaretString = s;

        } else {
            afterCaretString = s.substring(0, i);
        }

        String word = beforeCaretString + afterCaretString;
        System.out.println("Word " + word);
        return word;
    }

    private void showSuggestions(MouseEvent e, String word, Dictionary dictionary) {
        SpellingSuggester spellingSuggester = new SpellingSuggester(dictionary);
        List<String> list = spellingSuggester.getSuggestions(word);

        JPopupMenu popup = new JPopupMenu("Popup Menu");  

        if (list.isEmpty()) {
            JMenuItem menuItem = new JMenuItem("No suggestions");
            popup.add(menuItem);  //adding MenuItem
        } else {
            for (String str : list) {
                JMenuItem menuItem = new JMenuItem(str);
                popup.add(menuItem);  //adding MenuItem

                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textArea.replaceRange(str, start, start + word.length());
                    }
                });
            }
        }

        // add mispelled word to dictionary
        JMenuItem menuItem = new JMenuItem("Add to dictionary");
        popup.add(menuItem);  //adding MenuItem

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TO DO add word to dictionary
            }
        });
        popup.show(e.getComponent(), e.getX(), e.getY());
    }

    public void displayValidWordMessage() {
        JOptionPane.showMessageDialog(this, "Word correctly spelt");
    }
}
