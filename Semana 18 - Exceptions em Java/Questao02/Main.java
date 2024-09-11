package Questao02;

public class Main {
  public static void main(String[] args) throws ContaException {
    Conta minhaConta = new Conta();
    minhaConta.deposita(100);
    minhaConta.setLimite(100);
    minhaConta.saca(1000);
  }
}
