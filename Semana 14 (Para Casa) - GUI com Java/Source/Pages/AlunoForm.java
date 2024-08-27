package Source.Pages;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Source.DAO.Aluno;
import Source.DAO.AlunoDB;

public class AlunoForm extends JPanel {
  private JTextField numeroTextField;
  private JTextField matriculaTextField;
  private JTextField nomeTextField;
  private AlunoDB alunoDB;

  public AlunoForm() {
    setLayout(new GridBagLayout());

    initComponents();
  }

  private void initComponents() {
    alunoDB = new AlunoDB();

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.weightx = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);

    JLabel numeroLabel = new JLabel("Numero: ");
    JLabel matriculaLabel = new JLabel("Matricula: ");
    JLabel nomeLabel = new JLabel("Nome: ");

    numeroTextField = new JTextField(30);
    matriculaTextField = new JTextField(30);
    nomeTextField = new JTextField(30);

    numeroLabel.setLabelFor(numeroTextField);
    matriculaLabel.setLabelFor(matriculaLabel);
    nomeLabel.setLabelFor(nomeTextField);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.EAST;
    add(numeroLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    add(numeroTextField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    add(matriculaLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    add(matriculaTextField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    add(nomeLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 2;
    add(nomeTextField, gbc);
  }

  public void updateAluno(int alunoID) {
    Aluno aluno = alunoDB.getAluno(alunoID);

    if (aluno != null) {
      numeroTextField.setText(Integer.toString(aluno.getNumero()));
      matriculaTextField.setText(Long.toString(aluno.getMatricula()));
      nomeTextField.setText(aluno.getNome());
    } else {
      JOptionPane.showMessageDialog(null, "Não foi possivel encontrar nenhum aluno");
    }
  }

  public void deletaAluno(int alunoID) {
    if (alunoDB.deletaAluno(alunoID)) {
      JOptionPane.showMessageDialog(null, "Registro do aluno apagado com sucesso");
    } else {
      JOptionPane.showMessageDialog(null, "Não foi possivel encontrar nenhum aluno - Nenhum registro foi deletado");
    }
  }
}
