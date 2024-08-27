package Exemplos;

import javax.swing.*;
import java.awt.*;

public class LuzVermelhaApp extends JFrame {
    public LuzVermelhaApp() {
        super("Luz Vermelha");
        getContentPane().setLayout(new GridLayout(2,2));
        ComponenteLuzVermelha c1, c2, c3, c4;

        c1 = new ComponenteLuzVermelha(10);
        c2 = new ComponenteLuzVermelha(50);
        c3 = new ComponenteLuzVermelha(100);
        c4 = new ComponenteLuzVermelha(250);

        getContentPane().add(c1);
        getContentPane().add(c2);
        getContentPane().add(c3);
        getContentPane().add(c4);

        pack();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new LuzVermelhaApp();
    }
}
