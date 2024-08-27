package Reversi;

import javax.swing.*;
import java.awt.*;


public class PecaIcone extends JButton {

    private static int tamanho = 64;
    private Estado estado;
    private Image i1, i2;

    public PecaIcone() {
        super();
        setContentAreaFilled(false);
        estado = Estado.VAZIO;

        i1 = new ImageIcon("./assets/gon.jpg").getImage();
        i2 = new ImageIcon("./assets/killua.jpg").getImage();
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(64, 64);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (estado != Estado.VAZIO) {
            if (estado == Estado.BRANCO) {
                g2d.drawImage(i1, 0, 0, null);
            } else if (estado == Estado.PRETO) {
                g2d.drawImage(i2, 0, 0, null);
            }

//            RadialGradientPaint paint = new RadialGradientPaint(new Point2D.Double(tamanho / 3, tamanho / 3), 2 * tamanho / 3, new float[]{0f, 1f}, cores);
//            g2d.setPaint(paint);
//            g2d.fillOval(6, 6, getWidth() - 12, getHeight() - 12);
        }

        g2d.setColor(new Color(20, 150, 0));
        g2d.setStroke(new BasicStroke(3f));
//        g2d.drawOval(6, 6, getWidth() - 12, getHeight() - 12);
    }
}
