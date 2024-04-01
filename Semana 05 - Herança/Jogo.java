import javax.swing.JFrame;

public class Jogo {
  public static void main(String[] args) {
    JogoPainel jogoPainel = new JogoPainel();
    JFrame jogo = new JFrame("Tutorial");

    jogo.add(jogoPainel);
    jogo.pack();
    jogo.setSize(640, 480);
    jogo.setResizable(false);
    jogo.setVisible(true);
    jogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
