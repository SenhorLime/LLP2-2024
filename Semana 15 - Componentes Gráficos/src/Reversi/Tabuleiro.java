package Reversi;

import javax.swing.*;
import java.awt.*;

public class Tabuleiro extends JPanel {
    private PecaIcone[][] tabuleiro;

    public Tabuleiro() {
        setLayout(new GridLayout(8, 8));
        tabuleiro = new PecaIcone[8][8];

        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                tabuleiro[c][l] = new PecaIcone();
                add(tabuleiro[c][l]);
            }
        }

        tabuleiro[3][3].setEstado(Estado.BRANCO);
        tabuleiro[4][4].setEstado(Estado.BRANCO);
        tabuleiro[3][4].setEstado(Estado.PRETO);
        tabuleiro[4][3].setEstado(Estado.PRETO);
    }
}
