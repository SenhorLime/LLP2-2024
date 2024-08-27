package Source.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDB implements AlunoDAO {
  private List<Aluno> alunosList;

  public AlunoDB() {
    loadData();
  }

  private void loadData() {
    alunosList = new ArrayList<Aluno>();

    try (BufferedReader reader = new BufferedReader(new FileReader("Source/Data/dados.csv"))) {
      reader.readLine();

      String line;
      while ((line = reader.readLine()) != null) {
        String[] dataSplit = line.split(",");
        
        int numero = Integer.parseInt(dataSplit[0]);
        long matricula = Long.parseLong(dataSplit[1]);
        String nome = dataSplit[2];

        Aluno aluno = new Aluno();

        aluno.setNumero(numero);
        aluno.setMatricula(matricula);
        aluno.setNome(nome);

        alunosList.add(aluno);

      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  private void saveData() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Source/Data/dados.csv", true))) {
      writer.write("Num,Matricula, Nome");
      writer.newLine();

      for (Aluno aluno : alunosList) {
        writer.write(aluno.toString());
        writer.newLine();
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public boolean deletaAluno(int numero) {
    for (Aluno aluno : alunosList) {
      if (aluno.getNumero() == numero) {
        alunosList.remove(aluno);
        saveData();
        return true;
      }
    }

    return false;
  }

  @Override
  public List<Aluno> getAllAlunos() {
    return alunosList;
  }

  @Override
  public Aluno getAluno(int numero) {
    for (Aluno aluno : alunosList) {
      if (aluno.getNumero() == numero) {
        return aluno;
      }
    }

    return null;
  }

  @Override
  public void updateAluno(Aluno aluno) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateAluno'");
  }

}
