import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JogoAdivinhaNumero extends JFrame {
    public JogoAdivinhaNumero() {

        int sorteado,
                palpite = 0,
                tentativas = 0;

        // Scanner entrada = new Scanner(System.in);

        Random geradorDeAleatorios = new Random();
        sorteado = geradorDeAleatorios.nextInt(10) + 1;

        do {
            String numeroString = JOptionPane.showInputDialog(null, "Digite um numero: ", "Jogo - Advinha Numero",
                    JOptionPane.QUESTION_MESSAGE);

            palpite = Integer.valueOf(numeroString);

            tentativas++;

            dica(palpite, sorteado, tentativas);
        } while (palpite != sorteado);

    }

    public void dica(int palpite, int numero, int tentativas) {
        if (palpite > numero) {
            JOptionPane.showMessageDialog(null, "Seu palpite é maior que o número sorteado. \nVocê tentou "
                    + tentativas + " vezes antes de acertar!",
                    "Jogo - Advinha Numero",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {
            if (palpite < numero) {
                JOptionPane.showMessageDialog(null, "Seu palpite é menor que o número sorteado. \nVocê tentou "
                        + tentativas + " vezes antes de acertar!",
                        "Jogo - Advinha Numero",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null,
                        "Parabéns, você acertou! O número era " + numero + ". \nVocê tentou "
                                + tentativas + " vezes antes de acertar!",
                        "Jogo - Advinha Numero",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }

    public static void main(String[] args) {
        JogoAdivinhaNumero jogo = new JogoAdivinhaNumero();
    }

}