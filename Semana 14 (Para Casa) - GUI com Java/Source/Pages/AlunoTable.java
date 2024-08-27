package Source.Pages;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Source.DAO.Aluno;
import Source.DAO.AlunoDB;

public class AlunoTable extends JPanel {
  private JTable alunoJTable;
  private AlunoDB alunoDB;

  private String[] colunasIdentifier = {"Numero", "Matricula", "Nome"};

  public AlunoTable() {
    alunoJTable = new JTable();
    alunoDB = new AlunoDB();

    JScrollPane alunoScrollPane = new JScrollPane(alunoJTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    add(alunoScrollPane);
  }

  public void getAluno() {
    DefaultTableModel defaultTableModel = (DefaultTableModel) alunoJTable.getModel();
    defaultTableModel.setColumnIdentifiers(colunasIdentifier);
    
    for (Aluno aluno : alunoDB.getAllAlunos()) {
      String alunoData[] = aluno.toString().split(",");

      defaultTableModel.addRow(alunoData);
    }
  }
}
