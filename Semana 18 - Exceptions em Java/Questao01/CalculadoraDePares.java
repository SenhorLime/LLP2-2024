package Questao01;

public class CalculadoraDePares {
  public static int somar(int x, int y) throws NaoParException {
    if (x % 2 != 0 || y % 2 != 0) {
      throw new NaoParException("Um dos argumentos não é um número par");
    }

    return x + y;
  }

  public static int subtrair(int x, int y) throws NaoParException {
    if (x % 2 != 0 || y % 2 != 0) {
      throw new NaoParException("Um dos argumentos não é um número par");
    }

    return x - y;
  }

  public static int multiplicar(int x, int y) throws NaoParException {
    if (x % 2 != 0 || y % 2 != 0) {
      throw new NaoParException("Um dos argumentos não é um número par");
    }

    return x * y;
  }

  public static int dividir(int x, int y) throws NaoParException {
    if (x % 2 != 0 || y % 2 != 0) {
      throw new NaoParException("Um dos argumentos não é um número par");
    }

    return x / y;
  }
}
