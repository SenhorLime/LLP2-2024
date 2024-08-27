package Source.DAO;

import java.util.List;

public interface AlunoDAO {
  public List<Aluno> getAllAlunos();

  public Aluno getAluno(int numero);

  public void updateAluno(Aluno aluno);

  public boolean deletaAluno(int numero);
}
