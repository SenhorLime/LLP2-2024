package Source.Classes;

public class Gravacao extends Biblioteca {
  public Gravacao(int numeroCatalogo, int numeroDeCopias, String titulo, String midia) {
    super(numeroCatalogo, numeroDeCopias);
    this.titulo = titulo;
    this.midia = midia;
  }

  protected String titulo;
  protected String midia;

  @Override
  public void adquirir() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'adquirir'");
  }

  @Override
  public void retornar() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'retornar'");
  }

  @Override
  public String toString() {
    return numeroCatalogo + ";" + numeroDeCopias + ";" + titulo + ";;" + midia + ";" + ";;;;";
  }
}
