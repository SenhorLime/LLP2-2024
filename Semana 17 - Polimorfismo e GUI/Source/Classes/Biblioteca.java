package Source.Classes;

abstract class Biblioteca {
  protected int numeroCatalogo;
  protected int numeroDeCopias;

  Biblioteca(int numeroCatalogo, int numeroDeCopias) {
    this.numeroCatalogo = numeroCatalogo;
    this.numeroDeCopias = numeroDeCopias;
  }

  abstract public void adquirir();

  abstract public void retornar();

  @Override
  public String toString() {
    return numeroCatalogo + ";" + numeroDeCopias + ";;;;;;;;";
  }
}
