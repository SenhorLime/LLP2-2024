import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class JogoPainel extends JPanel implements Runnable, KeyListener {
  private ArrayList<Movel> bolinhas = new ArrayList<Movel>();
  private Thread animacao;
  private int tempoEspera = 10;
  private boolean jogando = false;

  public JogoPainel() {
    for (int i = 0; i < 10; i++) {
      Color cor = new Color((int) (Math.random() * 100), (int) (Math.random() * 100), (int) (Math.random() * 100));
      bolinhas.add(
          new Movel((int) (Math.random() * 10), (int) (Math.random() * 10), cor, (int) (Math.random() * 100)));
    }

    setBackground(Color.BLACK);
    addKeyListener(this);
    setFocusable(true);
  }

  public void addNotify() {
    super.addNotify();
    iniciarJogo();
  }

  private void iniciarJogo() {
    if (animacao == null || !jogando) {
      animacao = new Thread(this);
      animacao.start();
    }
  }

  public void run() {
    jogando = true;

    while (jogando) {
      for (Movel movel : bolinhas) {
        movel.move();
      }

      repaint();

      try {
        Thread.sleep(tempoEspera);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.exit(0);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (Movel movel : bolinhas) {
      movel.paintComponent(g);
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        System.out.println("Pressionando Up");
        break;
      case KeyEvent.VK_DOWN:
        break;
      case KeyEvent.VK_LEFT:
        break;
      case KeyEvent.VK_RIGHT:
        break;
      default:
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        System.out.println("Solto Up");
        break;
      case KeyEvent.VK_DOWN:
        break;
      case KeyEvent.VK_LEFT:
        break;
      case KeyEvent.VK_RIGHT:
        break;
      default:
        break;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }
}