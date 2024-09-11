package Source.Classes;

import java.time.LocalDate;

public class Filme extends Gravacao {
  public Filme(int numeroCatalogo, int numeroDeCopias, String titulo, String midia, String diretor,
      LocalDate dataDeLancamento,
      String distribuidor) {
    super(numeroCatalogo, numeroDeCopias, titulo, midia);
    this.diretor = diretor;
    this.dataDeLancamento = dataDeLancamento;
    this.distribuidor = distribuidor;

  }

  public String diretor;
  public LocalDate dataDeLancamento;
  public String distribuidor;

  @Override
  public String toString() {
    return numeroCatalogo + ";" + numeroDeCopias + ";" + titulo + ";;" + midia + ";" + diretor + ";" + dataDeLancamento
        + ";" + distribuidor + ";;";
  }
}
