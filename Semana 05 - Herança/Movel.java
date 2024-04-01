import java.awt.Color;
import java.awt.Graphics;

public class Movel {
  private static final int LARGURA = 625;
  private static final int ALTURA = 445;
  private int diametroBola = 30;
  private int x = 50, y = 50, velocidadeX = 1, velocidadeY = 1;
  private Color cor = Color.WHITE;

  public Movel() {
  }

  public Movel(int novoX, int novoY, Color novaColor, int novoTamanho) {
    x = novoX;
    y = novoY;
    cor = novaColor;
    diametroBola = novoTamanho;
  }

  public void move() {
    if (x >= LARGURA - diametroBola)
      velocidadeX *= -1;

    if (x <= 0)
      velocidadeX *= -1;

    if (y >= ALTURA - diametroBola)
      velocidadeY *= -1;

    if (y <= 0)
      velocidadeY *= -1;

    x += velocidadeX;
    y += velocidadeY;
  }

  public void paintComponent(Graphics g) {
    g.setColor(cor);
    g.drawOval(x, y, diametroBola, diametroBola);
  }
}
