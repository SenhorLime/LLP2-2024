package Rabisco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ComponenteParaRabiscos extends JComponent implements MouseListener, MouseMotionListener, KeyListener {
    private ArrayList<Point> pontos;
    private int size = 8;
    private int halfsize = size / 2;
    private Color cor;

    public ComponenteParaRabiscos(Color cor) {
        this.cor = cor;
        pontos = new ArrayList<Point>(1024);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(cor);

        for (Point p : pontos) {
            g2d.fillOval(p.x - halfsize, p.y - halfsize, size, size);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pontos.add(e.getPoint());
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        pontos.add(e.getPoint());
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        requestFocus();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_C) {
            pontos.clear();
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
