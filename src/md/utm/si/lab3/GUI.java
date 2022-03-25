package md.utm.si.lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI {
    JFrame frm;
    TextArea tOutput;
    JPanel p;
    JPanel p1;
    JLabel lInput;
    JTextField tInput;
    JLabel lOutput;
    JTextField tOput;
    JLabel lKey;
    JTextField tKey;
    JButton Encrypt;
    JButton Decrypt;

    public GUI(){
        frm=new JFrame("DES Encryption");

        tOutput = new TextArea();
        tOutput.setBackground(Color.WHITE);

        frm.add(tOutput);

        frm.setSize(700,400);

        frm.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        p = new JPanel();
        p1 = new JPanel();
        lInput = new JLabel("Input (hex)");
        tInput = new JTextField(20);
        lKey =new JLabel("Key (hex)");
        tKey=new JTextField(20);
        lOutput = new JLabel("Output (hex)");
        tOput = new JTextField(20);

        p.setLayout(new GridLayout(4,1));

        p.add(lInput);
        p.add(tInput);
        p.add(lKey);
        p.add(tKey);
        p.add(lOutput);
        p.add(tOput);

        Encrypt=new JButton("Encrypt");
        p.add(Encrypt);

        Decrypt=new JButton("Decrypt");
        p.add(Decrypt);

        p1.add(p);

        frm.add(p1,BorderLayout.NORTH);
    }
}