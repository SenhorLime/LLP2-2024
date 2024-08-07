package Source.DAOs.Artista;

public class Artista {
  private int codigo;
  private String nome;

  public Artista() {
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getCodigo() {
    return this.codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }
}
