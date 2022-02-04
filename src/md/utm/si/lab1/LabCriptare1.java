package md.utm.si.lab1;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabCriptare1 {

    private static final String ERROR_1 = "Carcterul nu este introdus";
    private static final String ERROR_2 = "Char is not input";
    private static final String ERROR_3 = "Nu este text criptat";

    private static JFrame f_base;
    private static JButton b_crp, b_dcrp;
    private static JLabel l_secret, l_text;
    private static JTextPane  tf_text, tf_result, tf_result_decr;
    private static JTextField tf_secret;
    private static JCheckBox cb_cezar, cb_viginere, cb_vernam;


    public static void main(String[] args) {

        createFrame();
        logic();
    }

    private static void logic() {

        b_crp.addActionListener(e -> {
            String text = tf_text.getText();
            String secret = tf_secret.getText();

            if(text.isEmpty()){
                tf_result.setText(ERROR_1);
                return;
            }

            if(secret.isEmpty()){
                tf_result.setText(ERROR_2);
                return;
            }
            if(cb_cezar.isSelected()){
                tf_result.setText(Cezar.encrypt(text, findPosition(secret.charAt(0))));
            } else if(cb_viginere.isSelected()){
                tf_result.setText(Viginere.encrypt(text, secret));
            } else {
                tf_result.setText(Vernam.encrypt(text, secret));
            }


        });

        b_dcrp.addActionListener(e -> {
            String text = tf_result.getText();
            String secret = tf_secret.getText();

            if(text.isEmpty()){
                tf_result_decr.setText(ERROR_1);
                return;
            }

            if(secret.isEmpty()){
                tf_result_decr.setText(ERROR_2);
                return;
            }

            if(cb_cezar.isSelected()){
                tf_result_decr.setText(Cezar.decrypt(text, findPosition(secret.charAt(0))));
            } else if(cb_viginere.isSelected()){
                tf_result_decr.setText(Viginere.decrypt(text, secret));
            } else {
                tf_result_decr.setText(Vernam.decrypt(text, secret));
            }


        });

        cb_cezar.addActionListener(e -> {
            cb_cezar.setSelected(true);
            cb_viginere.setSelected(false);
            cb_vernam.setSelected(false);
            tf_secret.setDocument(new JTextFieldLimit(1));
            tf_secret.setText("");

        });

        cb_viginere.addActionListener(e -> {
            cb_cezar.setSelected(false);
            cb_viginere.setSelected(true);
            cb_vernam.setSelected(false);
            tf_secret.setDocument(new JTextFieldLimit(10));
            tf_secret.setText("");

        });

        cb_vernam.addActionListener(e -> {
            cb_cezar.setSelected(false);
            cb_viginere.setSelected(false);
            cb_vernam.setSelected(true);
            tf_secret.setDocument(new JTextFieldLimit(10));
            tf_secret.setText("");

        });
    }

    private static void createFrame() {

        f_base = new JFrame("Securitate Lab 1");

        b_crp = new JButton("Criptare");
        b_dcrp = new JButton("Decripteaza");

        l_secret = new JLabel("Cheia");
        l_text = new JLabel("Text");

        tf_result = new JTextPane();
        tf_secret = new JTextField();
        tf_text = new JTextPane();
        tf_result_decr = new JTextPane();

        cb_cezar = new JCheckBox("Cezar");
        cb_cezar.setSelected(true);
        cb_viginere = new JCheckBox("Viginere");
        cb_vernam = new JCheckBox("Vernam");

        cb_cezar.setBounds(50, 475, 100,25);
        cb_viginere.setBounds(50, 495, 100,25);
        cb_vernam.setBounds(50, 515, 100,25);


        b_crp.setBounds(50, 375, 150, 75);
        b_dcrp.setBounds(575, 225, 150, 75);

        l_secret.setBounds(50, 50, 250, 50);
        tf_secret.setBounds(50, 125, 250, 50);
        tf_secret.setDocument(new JTextFieldLimit(1));

        l_text.setBounds(50, 200, 250, 50);
        tf_text.setBounds(50, 250, 500, 100);

        tf_result.setBounds(575, 100, 500, 100);

        tf_result_decr.setBounds(575, 350, 500, 100);
        tf_result_decr.setEditable(false);


        f_base.add(cb_cezar);
        f_base.add(cb_viginere);
        f_base.add(cb_vernam);
        

        f_base.add(b_crp);
        f_base.add(b_dcrp);
        f_base.add(l_secret);
        f_base.add(l_text);
        f_base.add(tf_result);
        f_base.add(tf_secret);
        f_base.add(tf_text);
        f_base.add(tf_result_decr);

        f_base.setSize(1200,700);
        f_base.setLayout(null);
        f_base.setVisible(true);
    }

    public static int findPosition(char inputLetter) {
        char inputLetterToLowerCase= Character.toLowerCase(inputLetter);
        int asciiValueOfinputChar = (int)inputLetterToLowerCase;
        int position = 96-asciiValueOfinputChar;
        return position;
    }


}


class JTextFieldLimit extends PlainDocument {
    private int limit;
    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    JTextFieldLimit(int limit, boolean upper) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
