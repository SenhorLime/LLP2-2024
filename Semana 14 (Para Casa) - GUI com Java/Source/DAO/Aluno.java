package Source.DAO;

public class Aluno {
  private int numero;
  private long matricula;
  private String nome;

  public Aluno() {
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public long getMatricula() {
    return matricula;
  }

  public void setMatricula(long matricula) {
    this.matricula = matricula;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return getNumero() + "," + getMatricula() + "," + getNome();
  }
}
