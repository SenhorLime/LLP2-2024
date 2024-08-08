import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InsereDado extends JFrame implements ActionListener {
  private JLabel labelNumero, labelMatricula, labelNome;
  private JTextField textNumero, textMatricula, textNome;
  private JButton buttonInserir, buttonLimpar;

  public InsereDado() {
    super("Dados");
    setLayout(new FlowLayout());

    labelNumero = new JLabel("Número: ");
    labelMatricula = new JLabel("Matrícula: ");
    labelNome = new JLabel("Nome: ");

    textNumero = new JTextField();
    textMatricula = new JTextField();
    textNome = new JTextField();

    textNumero.setColumns(10);
    textMatricula.setColumns(10);
    textNome.setColumns(10);

    buttonInserir = new JButton("Inserir");
    buttonLimpar = new JButton("Limpar");

    add(labelNumero);
    add(textNumero);

    add(labelMatricula);
    add(textMatricula);

    add(labelNome);
    add(textNome);

    add(buttonInserir);
    add(buttonLimpar);

    textNumero.addActionListener(this);
    textNumero.setEnabled(false);
    textNumero.setText(String.valueOf(getLastNumber() + 1));

    textMatricula.addActionListener(this);
    textMatricula.setInputVerifier(new InputVerifier() {
      @Override
      public boolean verify(JComponent input) {
        JTextField component = (JTextField) input;

        return component.getText().matches("^\\d{11}$");
      }
    });

    textNome.addActionListener(this);
    textNome.setInputVerifier(new InputVerifier() {
      @Override
      public boolean verify(JComponent input) {
        JTextField component = (JTextField) input;

        return component.getText().matches("\\b\\w+\\b.*\\b\\w+\\b");
      }
    });

    buttonInserir.addActionListener(this);
    buttonLimpar.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == buttonInserir) {
      insertDataInFile();
    }

    if (event.getSource() == buttonLimpar) {
      clearTextField();
    }
  }

  private void insertDataInFile() {
    String numero = textNumero.getText();
    String matricula = textMatricula.getText();
    String nome = textNome.getText();

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("dados.csv", true))) {
      writer.write(numero + "," + matricula + "," + nome + System.lineSeparator());
    } catch (Exception exception) {
      exception.printStackTrace();
    }

    clearTextField();
  }

  private int getLastNumber() {
    List<String> fileLines = new ArrayList<String>();
    String line;

    try (BufferedReader reader = new BufferedReader(new FileReader("dados.csv"))) {
      while ((line = reader.readLine()) != null) {
        fileLines.add(line);
      }
    } catch (Exception e) {
      System.err.println("Could not open file: " + e.getMessage());
    }

    String lastNumber = fileLines.get(fileLines.size() - 1).split(",")[0];

    return Integer.valueOf(lastNumber);
  }

  private void clearTextField() {
    textNumero.setText(String.valueOf(getLastNumber() + 1));
    textMatricula.setText("");
    textNome.setText("");
  }

  public static void main(String[] args) {
    InsereDado window = new InsereDado();
    window.setSize(200, 150);
    window.setVisible(true);
    window.setResizable(false);
  }
}