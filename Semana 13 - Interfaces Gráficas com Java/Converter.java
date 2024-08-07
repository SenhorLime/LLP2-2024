import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Converter extends JFrame implements ActionListener {
  private JLabel jLabelForTextField;
  private JLabel jLabelForResult;
  private JTextField jText;
  private JButton jButton;

  private float celsiusToFahrenheit(float celsiusTemp) {
    float fahrenheitTemp = ((celsiusTemp * 9) / 5) + 32;

    return fahrenheitTemp;
  }

  public Converter(String titulo) {
    super(titulo);
    setLayout(new FlowLayout());

    jLabelForTextField = new JLabel("Temperatura em celsius: ");
    jLabelForResult = new JLabel("Temperatura em Fahrenheit: " + celsiusToFahrenheit(Float.valueOf(0)));
    jText = new JTextField("0");
    jButton = new JButton("Converter");

    jText.setColumns(5);

    add(jLabelForTextField);
    add(jText);
    add(jButton);
    add(jLabelForResult);

    jText.addActionListener(this);
    jButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == jText) {
      JOptionPane.showMessageDialog(this, "Caixa de texto: " + event.getActionCommand());
    } else {
      float convertedTemp = celsiusToFahrenheit(Float.valueOf(this.jText.getText()));
      jLabelForResult.setText("Temperatura em Fahrenheit: " + convertedTemp);
    }
  }

  public static void main(String[] args) {
    Converter jogo = new Converter("C° para °F");
    jogo.setSize(300, 130);
    jogo.setVisible(true);
  }
}
