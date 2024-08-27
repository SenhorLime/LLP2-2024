package Rabisco;

import javax.swing.*;
import java.awt.*;

public class AppRabiscos extends JFrame {
    public AppRabiscos() {
        ComponenteParaRabiscos component = new ComponenteParaRabiscos(new Color(50, 120, 250));
        Container cp = getContentPane();
        cp.add(component);

        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new AppRabiscos();
    }
}

