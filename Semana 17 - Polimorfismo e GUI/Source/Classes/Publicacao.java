package Source.Classes;

public class Publicacao extends Biblioteca {
  Publicacao(int numeroCatalogo, int numeroDeCopias, String titulo, String editor) {
    super(numeroCatalogo, numeroDeCopias);
    this.titulo = titulo;
    this.editor = editor;
  }

  private String titulo;
  private String editor;

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
    return numeroCatalogo  + ";" + numeroDeCopias + ";" + titulo + ";" + editor + ";;;;;;";
  }
}
