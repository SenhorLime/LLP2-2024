package Source.Classes;

public class Software extends Gravacao {
  public Software(int numeroCatalogo, int numeroDeCopias, String titulo, String midia, int versao, String plataforma) {
    super(numeroCatalogo, numeroDeCopias, titulo, midia);
    this.versao = versao;
    this.plataforma = plataforma;
  }

  public int versao;
  public String plataforma;

  @Override
  public String toString() {
    return numeroCatalogo + ";" + numeroDeCopias + ";" + titulo + ";;" + midia + ";;;;" + versao + ";" + plataforma;
  }
}
